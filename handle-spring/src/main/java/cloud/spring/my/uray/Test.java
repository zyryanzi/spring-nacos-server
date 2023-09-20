package cloud.spring.my.uray;

import cloud.spring.my.spring.UrayApplicationContext;
import cloud.spring.my.uray.service.OrderService;
import cloud.spring.my.uray.service.UserService;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        UrayApplicationContext applicationContext = new UrayApplicationContext(AppConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");

        userService.callOrder();
        orderService.callUser();

    }

}
