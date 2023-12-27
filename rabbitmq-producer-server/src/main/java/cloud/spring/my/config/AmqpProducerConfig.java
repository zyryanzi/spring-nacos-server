package cloud.spring.my.config;

import cloud.spring.my.base.common.constant.AmqpConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

@Slf4j
@Configuration
public class AmqpProducerConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean("customExchange")
    public CustomExchange customExchange() {
        return new CustomExchange(AmqpConstant.CUSTOM_EXCHANGE_NAME, ExchangeTypes.FANOUT, Boolean.TRUE, Boolean.FALSE);
    }

    @Bean("beyondExchange")
    public DirectExchange beyondExchange() {
        Message message = new Message("test 连接".getBytes());
        rabbitTemplate.send(message);
        return ExchangeBuilder
                .directExchange(AmqpConstant.EXCHANGE_NAME)
                .durable(Boolean.TRUE)
                .build();
    }

    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(AmqpConstant.CONFIRM_EXCHANGE_NAME, Boolean.TRUE, Boolean.FALSE);
    }

    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(AmqpConstant.BACKUP_EXCHANGE_NAME, Boolean.TRUE, Boolean.FALSE);
    }


    @Bean("beyondQueue")
    public Queue beyondQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put(AmqpConstant.ALTERNATIVE_EXCHANGE, AmqpConstant.BACKUP_EXCHANGE_NAME);

        return QueueBuilder.durable(AmqpConstant.QUEUE_NAME).withArguments(arguments).build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(AmqpConstant.CONFIRM_QUEUE_NAME).build();
    }

    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(AmqpConstant.BACKUP_QUEUE_NAME).build();
    }

    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(AmqpConstant.WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding beyondBindingBeyond(
            @Qualifier("beyondExchange") DirectExchange beyondExchange,
            @Qualifier("beyondQueue") Queue beyondQueue) {
        return BindingBuilder.bind(beyondQueue).to(beyondExchange).with(AmqpConstant.ROUTING_KEY);
    }

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
