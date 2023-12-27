package cloud.spring.my.shortdomain.controller;

import cloud.spring.my.base.common.api.vo.Result;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShortDomainController
 * @Author: uray
 * @Date: 2022-02-28 22:18
 **/
@RestController
@RequestMapping(("/domain"))
public class ShortDomainController {

    @GetMapping("/get")
    @SentinelResource()
    public Result<?> getShortDomain(@RequestParam(name = "url", required = false) String url) {
        return Result.ok("short domain");
    }

}
