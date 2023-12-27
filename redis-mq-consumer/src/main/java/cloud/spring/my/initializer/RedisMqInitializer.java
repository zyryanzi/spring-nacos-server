package cloud.spring.my.initializer;

import cloud.spring.my.annotation.MessageConsumer;
import cloud.spring.my.annotation.MessageHandler;
import cloud.spring.my.annotation.RedisMessageListener;
import cloud.spring.my.handler.AbstractMessageHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 启动时，扫描所有@MessageConsumer注解的类，并初始化
 *
 */
@Slf4j
@NoArgsConstructor
@Component
public class RedisMqInitializer implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) {
        Map<RedisMessageListener.Mode, AbstractMessageHandler> handlerMap = getHandlerMap();
        applicationContext.getBeansWithAnnotation(MessageConsumer.class).values().parallelStream().forEach(consumer -> {
            Method[] methods = consumer.getClass().getDeclaredMethods();
            if (ArrayUtils.isNotEmpty(methods)) {
                Arrays.stream(methods).parallel().forEach(method -> startMessageListener(method, handlerMap));
            }
        });
    }


    /**
     * 取得 监听模式 和 消息处理器映射
     *
     * @return
     */
    private Map<RedisMessageListener.Mode, AbstractMessageHandler> getHandlerMap() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(MessageHandler.class);
        return beans.values().stream().collect(Collectors.toMap(
                bean -> bean.getClass().getAnnotation(MessageHandler.class).value(),
                AbstractMessageHandler.class::cast)
        );
    }

    /**
     * 启动消息监听器
     *
     * @param method
     * @param handlerMap
     */
    private void startMessageListener(Method method, Map<RedisMessageListener.Mode, AbstractMessageHandler> handlerMap) {
        RedisMessageListener annotation = method.getAnnotation(RedisMessageListener.class);
        if (ObjectUtils.isEmpty(annotation)) {
            return;
        }
        if (!handlerMap.containsKey(annotation.mode())) {
            log.error("--->> 未找到对应的处理器");
        }
        handlerMap.get(annotation.mode()).invokeMessage(method);
    }

}
