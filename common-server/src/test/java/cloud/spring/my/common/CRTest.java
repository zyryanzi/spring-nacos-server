package cloud.spring.my.common;

import cloud.spring.my.common.service.impl.A;
import cloud.spring.my.common.service.impl.B;
import cloud.spring.my.common.utils.sping.SpringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CRTest {

    @Test
    public void testCircleRef() {
        System.out.println("--->>> testCircleRef");
        A a = SpringUtils.getBean("a");
        B b = SpringUtils.getBean("b");
        a.testA();
        b.testB();
    }

}
