package cloud.spring.my.consumer;

import cloud.spring.my.annotation.MessageConsumer;
import cloud.spring.my.annotation.RedisMessageListener;
import cloud.spring.my.base.common.constant.AmqpConstant;
import cloud.spring.my.base.common.constant.RedisStreamMqConstant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@MessageConsumer
public class RedisStreamConsumer {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 流模式消费者1
     */
    @RedisMessageListener(
            streamKey = RedisStreamMqConstant.QUEUE_NAME,
            consumerGroup = RedisStreamMqConstant.CONSUMER_GROUP,
            consumerName = RedisStreamMqConstant.CONSUMER_1,
            mode = RedisMessageListener.Mode.STREAM)
    public void streamConsumer1(ObjectRecord<String, String> message) {
        log.info("--->> 进入消费者：{}, 分组：{}, 消息内容：{}",
                RedisStreamMqConstant.CONSUMER_1, RedisStreamMqConstant.CONSUMER_GROUP, message);
        // 消息消费通知
        redisTemplate.opsForStream().acknowledge(AmqpConstant.QUEUE_NAME, "redis-stream-group", message.getId());
    }

    /**
     * 流模式消费者2，
     * 与消费者1消费组相同，为竞争关系
     */
    @RedisMessageListener(
            streamKey = RedisStreamMqConstant.QUEUE_NAME,
            consumerGroup = RedisStreamMqConstant.CONSUMER_GROUP,
            consumerName = RedisStreamMqConstant.CONSUMER_1,
            mode = RedisMessageListener.Mode.STREAM)
    public void streamConsumer2(ObjectRecord<String, String> message) {
        log.info("--->> 进入消费者：{}, 分组：{}, 消息内容：{}",
                RedisStreamMqConstant.CONSUMER_2, RedisStreamMqConstant.CONSUMER_GROUP, message);
        // 消息消费通知, 模拟发送通知失败
//        redisTemplate.opsForStream().acknowledge(AmqpConstant.QUEUE_NAME, "redis-stream-group", message.getId());
    }

    /**
     * 流模式消费者3
     * 消费未通知消息
     */
    @RedisMessageListener(
            streamKey = RedisStreamMqConstant.QUEUE_NAME,
            consumerGroup = RedisStreamMqConstant.CONSUMER_GROUP,
            consumerName = RedisStreamMqConstant.CONSUMER_1,
            pending = true,
            mode = RedisMessageListener.Mode.STREAM)
    public void streamConsumer3(ObjectRecord<String, String> message) {
        log.info("--->> 进入消费者：{}, 消费味成功通知消息, 分组：{}, 消息内容：{}",
                RedisStreamMqConstant.CONSUMER_PENDING, RedisStreamMqConstant.CONSUMER_GROUP, message);
        // 消息消费通知
        redisTemplate.opsForStream().acknowledge(AmqpConstant.QUEUE_NAME, "redis-stream-group", message.getId());
    }

}
