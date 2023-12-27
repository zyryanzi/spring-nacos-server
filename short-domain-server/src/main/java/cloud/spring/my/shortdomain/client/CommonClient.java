package cloud.spring.my.shortDomain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: CommonClient
 * @Author: uray
 * @Date: 2022-02-28 22:14
 **/
//@Component
//@FeignClient(name = "beyond-common-server" )
public interface CommonClient {

    @LoadBalanced
    @GetMapping("/genid/sfid")
    Long genSFId();

}
