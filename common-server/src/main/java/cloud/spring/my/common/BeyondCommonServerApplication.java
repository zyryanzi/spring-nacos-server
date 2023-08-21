package cloud.spring.my.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
// 不然会报 "Exception in monitor thread while connecting to server localhost:27017"
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication
@EnableDiscoveryClient
public class BeyondCommonServerApplication
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BeyondCommonServerApplication.class, args);
        String domain = applicationContext.getEnvironment().getProperty("mydomain");
        System.out.println("common server is running on cloud --->>> " + domain);
    }
}
