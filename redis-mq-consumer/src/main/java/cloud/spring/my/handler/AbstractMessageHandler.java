package cloud.spring.my.handler;

import jakarta.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * 消息处理器
 *
 */
public abstract class AbstractMessageHandler implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 执行消息监听逻辑，由子类实现
     * @param method    注解的方法
     */
    public abstract void invokeMessage(Method method);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
