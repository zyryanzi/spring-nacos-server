package cloud.spring.my.shortdomain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: CommonClient
 * @Author: uray
 * @Date: 2022-02-28 22:14
 **/
@Component
@FeignClient(name = "beyond-common-server" )
public interface CommonClient {

    @GetMapping("/genid/sfid")
    Long genSFId();

}
