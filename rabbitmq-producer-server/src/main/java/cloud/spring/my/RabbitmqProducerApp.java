package cloud.spring.my;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class RabbitmqProducerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RabbitmqProducerApp.class);
        System.out.println( "--->> Amqp Start" );
    }
}
