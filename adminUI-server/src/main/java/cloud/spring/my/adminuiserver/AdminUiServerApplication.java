package cloud.spring.my.adminuiserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class AdminUiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminUiServerApplication.class, args);
	}

}
