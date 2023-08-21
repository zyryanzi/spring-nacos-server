package cloud.spring.my.shortdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 */

@EnableDiscoveryClient
@EnableFeignClients
// 不然会报 "Exception in monitor thread while connecting to server localhost:27017"
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication
public class BeyondShortdomainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BeyondShortdomainApplication.class, args);
        String domain = applicationContext.getEnvironment().getProperty("mydomain");
        System.out.println("short cloud.spring.my.study.domain is running on cloud --->>> " + domain);
    }
}

