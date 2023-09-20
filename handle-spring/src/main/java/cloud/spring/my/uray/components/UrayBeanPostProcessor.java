package cloud.spring.my.uray.components;

import cloud.spring.my.spring.interfaces.BeanPostProcessor;
import cloud.spring.my.spring.annotation.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class UrayBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("--->>> exec postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("--->> exec postProcessAfterInitialization");

        if ("userService".equals(beanName)) {
            Proxy.newProxyInstance(UrayBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.println("--->>> 执行代理逻辑");
                // 返回代理对象
                return method.invoke(bean, args);
            });
        }

        return bean;
    }
}
