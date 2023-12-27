package cloud.spring.my.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;


/**
 * 消息消费者注解
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MessageConsumer {

    String value() default "";

}
