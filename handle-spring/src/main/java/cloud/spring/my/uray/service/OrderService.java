package cloud.spring.my.uray.service;

import cloud.spring.my.spring.annotation.Autowired;
import cloud.spring.my.spring.annotation.Component;

@Component("orderService")
public class OrderService {

    @Autowired
    UserService userService ;

    public void callUser() {
        System.out.println("--- call user");
        userService.callOrder();
    }

}
