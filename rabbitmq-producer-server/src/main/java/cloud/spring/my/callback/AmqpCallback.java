package cloud.spring.my.callback;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 队列回退，优先级低于 备份交换机
 */
@Slf4j
@Component
public class AmqpCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 在应用启动后自动调用的方法，用于初始化操作
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData == null ? "" : correlationData.getId();
        if (ack) {
            log.info("消息[{}]发送成功", id);
        } else {
            log.info("消息[{}]发送失败", id);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("--->> {}", returned.toString());
    }

}
