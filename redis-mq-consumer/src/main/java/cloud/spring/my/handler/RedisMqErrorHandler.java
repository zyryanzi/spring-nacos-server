package cloud.spring.my.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * 异常处理类
 */
@Slf4j
@Component
public class RedisMqErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("--->> error: {}", t.getMessage());
        t.printStackTrace();
        // todo 将异常信息持久化，进入日志记录
    }

}
