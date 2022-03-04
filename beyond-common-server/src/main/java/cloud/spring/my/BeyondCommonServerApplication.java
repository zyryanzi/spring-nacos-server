package cloud.spring.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
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
