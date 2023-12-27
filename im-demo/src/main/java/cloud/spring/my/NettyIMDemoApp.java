package cloud.spring.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class NettyIMDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(NettyIMDemoApp.class, args);
        System.out.println("--->> Hello NettyIM!");
    }
}
