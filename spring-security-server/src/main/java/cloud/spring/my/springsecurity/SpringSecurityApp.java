package cloud.spring.my.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("cloud.spring.my.springsecurity.mapper")
public class SpringSecurityApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringSecurityApp.class);
        System.out.println( "-->>> Spring Security already boot!" );
    }
}
