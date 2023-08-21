package cloud.spring.my.springgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
// 不然会报 "Exception in monitor thread while connecting to server localhost:27017"
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication
//@EnableDiscoveryClient
public class SpringGatewayApp
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringGatewayApp.class, args);
        String domain = applicationContext.getEnvironment().getProperty("mydomain");
        System.out.println("spring is running on cloud --->>> " + domain);
    }
}
