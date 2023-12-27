package cloud.spring.my.service.impl;

import cloud.spring.my.service.ISendToStream;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.AMQImpl;
import net.bytebuddy.implementation.bind.ArgumentTypeResolver;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.support.ArgumentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

@RestController
public class SendToStreamImpl implements ISendToStream {

    @Autowired
    RabbitMessagingTemplate rabbitTemplate;

    @GetMapping(name = "/send")
    @Override
    public void send(String msg) {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        rabbitTemplate.send(message);
        rabbitTemplate.send("beyond-exchange", message);
        rabbitTemplate.send("beyond-exchange", "beyond-routing-key", message);

        ConnectionFactory connectionFactory = new ConnectionFactory();
//        Connection connection = connectionFactory.newConnection();
//        Channel channel = connection.createChannel();
//        String s = "x-dead-letter-exchange";
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "deadExchangeName");
        arguments.put("x-dead-letter-routing-key", "deadRoutingKeyName");

    }

}
