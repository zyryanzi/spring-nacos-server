package cloud.spring.my.handler;

import cloud.spring.my.annotation.MessageHandler;
import cloud.spring.my.annotation.RedisMessageListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Redis Stream 形式实现消息队列
 */
@MessageHandler(value = RedisMessageListener.Mode.STREAM)
@Slf4j
public class RedisStreamMessageHandler extends AbstractMessageHandler {

    /**
     * Redis Stream 监听容器，用来存放监听器
     */
    @Resource
    @Qualifier("redisStreamMsgListenerContainer")
    StreamMessageListenerContainer<String, ObjectRecord<String, String>> redisStreamMsgListenerContainer;

    @Override
    public void invokeMessage(Method method) {
        RedisMessageListener annotation = method.getAnnotation(RedisMessageListener.class);
        if (ObjectUtils.isEmpty(annotation)) {
            return;
        }

        String streamKey = annotation.streamKey();
        String consumerGroup = annotation.consumerGroup();
        String consumerName = annotation.consumerName();
        boolean pending = annotation.pending();
        // 检查并创建组
        checkAndCreateGroup(streamKey, consumerGroup);
        // 取得队列偏移量
        StreamOffset<String> offset = getOffset(streamKey, pending);
        // 消费者
        Consumer consumer = Consumer.from(consumerGroup, consumerName);

        StreamMessageListenerContainer.ConsumerStreamReadRequest<String> request =
                StreamMessageListenerContainer.StreamReadRequest
                        .builder(offset)
                        .errorHandler(new RedisMqErrorHandler())
                        .cancelOnError(e -> true)
                        .consumer(consumer)
                        .autoAcknowledge(false)
                        .build();

        redisStreamMsgListenerContainer.register(request, message -> {
            Class<?> declaringClass = method.getDeclaringClass();
            Object bean = applicationContext.getBean(declaringClass);
            try {
                // 执行注解的方法
                method.invoke(bean, message);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 获取执行偏移量 offset
     * 如果是为ack队列，则从头开始，否则从上次执行的位置开始
     *
     * @param streamKey 队列key
     * @param pending   是否已ack， true: 异常队列，false: 正常ack队列
     * @return  streamOffset<String>
     */
    private StreamOffset<String> getOffset(String streamKey, boolean pending) {
        if (pending) {
            // 尚未 ack, 从 0 开始
            return StreamOffset.create(streamKey, ReadOffset.from("0"));
        }
        return StreamOffset.create(streamKey, ReadOffset.lastConsumed());
    }

    /**
     * 检查组是否存在、如不存在则创建
     *
     * @param streamKey     队列key
     * @param consumerGroup 分组名
     */
    private void checkAndCreateGroup(String streamKey, String consumerGroup) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(streamKey))) {
            // 指定组不存在，创建
            createGroup(streamKey, consumerGroup);
        }

        StreamOperations<String, Object, Object> operations = redisTemplate.opsForStream();
        StreamInfo.XInfoGroups groups = operations.groups(streamKey);
        if (groups.isEmpty() || groups.stream().noneMatch(g -> consumerGroup.equals(g.groupName()))) {
            // 指定组不存在，创建
            createGroup(streamKey, consumerGroup);
        }

        groups.stream().forEach(group -> {
            log.info("--->> XInfoGroups: {}", group);
            StreamInfo.XInfoConsumers consumers = operations.consumers(streamKey, consumerGroup);
            log.info("--->> XInfoConsumers: {}", consumers);
        });
    }

    /**
     * 创建消息消费组
     *
     * @param streamKey     队列名
     * @param consumerGroup 分组名
     */
    private void createGroup(String streamKey, String consumerGroup) {
        redisTemplate.opsForStream().createGroup(streamKey, consumerGroup);
        log.info("--->> 成功创建队列分组：{}", consumerGroup);
    }
}
