package cloud.spring.my.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MessageHandler {

    RedisMessageListener.Mode value() default RedisMessageListener.Mode.STREAM;

}
