package cloud.spring.my.redis;

import cloud.spring.my.redis.service.IShortDomainService;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest
        extends TestCase {

    @Autowired
    IShortDomainService shortDomainService;

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp() {
        shortDomainService.save();
    }
}
