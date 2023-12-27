package cloud.spring.my;

import io.lettuce.core.RedisChannelWriter;
import io.lettuce.core.cluster.RedisAdvancedClusterReactiveCommandsImpl;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
import io.lettuce.core.cluster.topology.ClusterTopologyRefresh;
import io.lettuce.core.cluster.topology.TopologyComparators;
import io.lettuce.core.protocol.ConnectionFacade;
import io.lettuce.core.protocol.DefaultEndpoint;
import io.netty.channel.ChannelHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.ClusterNodeResourceProvider;
import org.springframework.data.redis.connection.ClusterTopologyProvider;
import org.springframework.data.redis.core.RedisCommand;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableRedisHttpSession
public class RedisMqConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RedisMqConsumerApp.class);
        System.out.println( "--->> Hello RedisMqConsumer!" );
    }
}
