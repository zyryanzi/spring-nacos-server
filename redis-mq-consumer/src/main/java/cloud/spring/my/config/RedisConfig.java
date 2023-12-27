package cloud.spring.my.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableCaching
@Configuration
public class RedisConfig implements CachingConfigurer {

    @Value("${spring.data.redis.password}")
    private String password;

    /**
     * 启用集群
     */
    @Value("${spring.data.redis.lettuce.cluster.enabled}")
    private Boolean clusterEnabled;
    @Value("${spring.data.redis.lettuce.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.data.redis.lettuce.cluster.connectTimeout}")
    private int connectTimeout;

    @Value("${spring.data.redis.lettuce.commandTimeout}")
    private int commandTimeout;


    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        if (!clusterEnabled) {
            return new LettuceConnectionFactory();
        }

        String[] nodes = clusterNodes.split(",");
        List<RedisNode> listNodes = new ArrayList<>();
        for (String node : nodes) {
            String[] ipAndPort = node.split(":");
            RedisNode redisNode = new RedisNode(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
            listNodes.add(redisNode);
        }
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setClusterNodes(listNodes);
        redisClusterConfiguration.setPassword(password);

        // 配置集群自动刷新拓扑
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                //按照周期刷新拓扑，这个在配置文件中配了 spring.data.redis.lettuce.cluster.refresh.period
//                .enablePeriodicRefresh(Duration.ofSeconds(refreshPeriod))
                .enableAllAdaptiveRefreshTriggers() //根据事件刷新拓扑
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                // lettuce 连接超时时间,超时后才会使用新的拓扑信息重新建立连接
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(connectTimeout)))
                .topologyRefreshOptions(topologyRefreshOptions)
                .build();

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(commandTimeout))
                .readFrom(ReadFrom.REPLICA_PREFERRED) // 优先从副本读取
                .clientOptions(clusterClientOptions)
                .build();

        return new LettuceConnectionFactory(redisClusterConfiguration, clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = jacksonSerializer();
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, Object> serializationContext = builder
                .key(StringRedisSerializer.UTF_8)
                .value(new GenericJackson2JsonRedisSerializer())
                .hashKey(StringRedisSerializer.UTF_8)
                .hashValue(new GenericJackson2JsonRedisSerializer())
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    @Bean("redisStreamMsgListenerContainer")
    public StreamMessageListenerContainer<String, ObjectRecord<String, String>> redisStreamMsgListenerContainer(
            LettuceConnectionFactory lettuceConnectionFactory,
            @Qualifier("redisListenerContainerExecutor") ThreadPoolTaskExecutor executor) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .batchSize(1)
                        .pollTimeout(Duration.ofSeconds(5))
                        .targetType(String.class)
                        .executor(executor)
                        .build();

        StreamMessageListenerContainer<String, ObjectRecord<String, String>> listenerContainer
                = StreamMessageListenerContainer.create(lettuceConnectionFactory, options);

        listenerContainer.start();
        return listenerContainer;
    }

    private Jackson2JsonRedisSerializer<Object> jacksonSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    }


}
