package cloud.spring.my.shortdomain.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class User {

    @Autowired
    private RestTemplate restTemplate;


}
