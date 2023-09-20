package cloud.spring.my.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("a")
@Scope("singleton")
public class A {

    @Autowired
    B b;

    public void testA(){
        System.out.println("--->>> call b");
        b.testB();
    }

}
