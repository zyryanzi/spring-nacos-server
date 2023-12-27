package cloud.spring.my.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注解，用于标注方法为消息监听器
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RedisMessageListener {

    String streamKey() default "";

    String consumerGroup() default "";

    String consumerName() default "";

    boolean pending() default false;

    Mode mode() default Mode.STREAM;

    /**
     * 消息监听器模式
     * 每种模式至多对应一个处理器
     */
    public enum Mode {
        // 流模式
        STREAM,
        // 发布订阅模式
        PUB_SUB,
        // 初级列表模式
        LIST,
        ;
    }
}
