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
public class SpringNacosApplication
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringNacosApplication.class, args);
        String domain = applicationContext.getEnvironment().getProperty("beyound.domain");
        System.out.println("spring is running on cloud --->>> " + domain);
    }
}
