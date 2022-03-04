package cloud.spring.my.shortdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
public class BeyondShortdomainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BeyondShortdomainApplication.class, args);
        String domain = applicationContext.getEnvironment().getProperty("mydomain");
        System.out.println("short domain is running on cloud --->>> " + domain);
    }
}
