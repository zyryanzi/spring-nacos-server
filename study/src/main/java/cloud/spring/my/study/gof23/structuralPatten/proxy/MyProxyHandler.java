package cloud.spring.my.study.gof23.structuralPatten.proxy;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于自动生成代理类
 */
@Data
public class MyProxyHandler implements InvocationHandler {

    /**
     * 被代理的目标
     */
    private Object target;

    /**
     * 生成代理类
     *
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 生成的代理类 执行目标方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

}
