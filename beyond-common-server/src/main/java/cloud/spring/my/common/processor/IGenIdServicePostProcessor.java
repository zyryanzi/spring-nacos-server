package cloud.spring.my.common.processor;

import cloud.spring.my.common.service.IGenIdService;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 此类使用了 后处理进程 + 代理 向指定类型方法执行增强
 * @ClassName: IGenIdServicePostProcessor
 * @Author: uray
 * @Date: 2022-03-04 11:27
 **/
public class IGenIdServicePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (!(bean instanceof IGenIdService)) {// 不是目标类型 绕过
            return bean;
        }

        Map<String, Object> map = new ConcurrentHashMap<>(10);
        if (map.get(beanName) != null) {// 已被代理 绕过
            return map.get(beanName);
        }

        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces == null || interfaces.length < 1) {// 没有实现接口 绕过
            return bean;
        }

        final Object finalBean = bean;
        Object proxyObj = Proxy.newProxyInstance(bean.getClass().getClassLoader(), interfaces, (proxy, method, args) -> {
            Object result;
            if ("equals".equals(method.getName()) || "toString".equals(method.getName())) {
                // 不需要增强的方法直接执行
                result = method.invoke(finalBean, args);
            } else {
                // 此处为增强内容，例如日志
                System.out.println("===>>> method: " + method.getName());
                result = method.invoke(finalBean, args);
            }
            return result;
        });

        map.put(beanName, proxyObj);
        return proxyObj;
    }

}
