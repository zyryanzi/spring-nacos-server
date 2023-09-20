package cloud.spring.my.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("b")
public class B {

    @Autowired
    A a;

    public void testB(){
        System.out.println("--->>> call a");
        a.testA();
    }
}
