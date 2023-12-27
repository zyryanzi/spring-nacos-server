package cloud.spring.my.springgateway.fallback.sentinel;

import cloud.spring.my.springgateway.enums.SentinelErrorInfoEnum;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GatewaySentinelExceptionConfig {

    @PostConstruct
    public void init() {

        BlockRequestHandler blockRequestHandler = (serverWebExchange, ex) -> {
            SentinelErrorInfoEnum errorInfo = SentinelErrorInfoEnum.getErrorByException(ex);
            String message;
            if (ObjectUtil.isNotEmpty(errorInfo)) {
                message = errorInfo.getError();
            } else {
                message = "Unknown sentinel flow limit.";
            }
            Map<String, String> bodyMap = new HashMap<>(8);
            bodyMap.put("code", HttpStatus.TOO_MANY_REQUESTS.toString());
            bodyMap.put("message", message);

            return ServerResponse
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(bodyMap));
        };


        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

}
