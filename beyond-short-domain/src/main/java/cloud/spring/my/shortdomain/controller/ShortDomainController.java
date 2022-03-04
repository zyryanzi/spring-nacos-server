package cloud.spring.my.shortdomain.controller;

import cloud.spring.my.base.common.api.vo.Result;
import cloud.spring.my.shortdomain.client.CommonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShortDomainController
 * @Author: uray
 * @Date: 2022-02-28 22:18
 **/
@RestController("/shortdomain")
public class ShortDomainController {

    @Autowired
    CommonClient commonClient;

    @GetMapping("/getShortDomain")
    public Result<?> getShortDomain(@RequestParam("url") String url) {
        return Result.ok(commonClient.genSFId());
    }
}
