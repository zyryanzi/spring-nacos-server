package cloud.spring.my.uray.service;

import cloud.spring.my.spring.annotation.Autowired;
import cloud.spring.my.spring.annotation.Component;
import cloud.spring.my.spring.annotation.Scope;
import cloud.spring.my.spring.interfaces.BeanNameAware;
import cloud.spring.my.spring.interfaces.InitializingBean;

@Component("userService")
@Scope("prototype")
public class UserService implements BeanNameAware, InitializingBean, org.springframework.beans.factory.InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
    }

    public void callOrder() {
        System.out.println("--- call order");
        orderService.callUser();
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("--->> set properties");
    }

}
