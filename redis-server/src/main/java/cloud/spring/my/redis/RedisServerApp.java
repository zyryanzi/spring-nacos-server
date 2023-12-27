package cloud.spring.my.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RedisServerApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisServerApp.class);
    }
}
