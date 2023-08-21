package cloud.spring.my.springsecurity;

import cloud.spring.my.common.utils.HashUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        System.out.println(HashUtils.getMurmur3_128("asdasf"));
        System.out.println(HashUtils.getMurmur3_128("sadsasdffasdfasdfasffasddfjlakf;laksfdfasjfds"));
        System.out.println(HashUtils.getMurmur3_128("skdfjslkdfjflsajaidfwqwiejaksadfioqjokqwepfweftiqefaslkfsjfjjoqweifjowifjowiqpjefjoiwefskdfjslkdfjflsajaidfwqwiejaksadfioqjokqwepfweftiqefaslkfsjfjjoqweifjowifjowiqpjefjoiwefskdfjslkdfjflsajaidfwqwiejaksadfioqjokqwepfweftiqefaslkfsjfjjoqweifjowifjowiqpjefjoiwef"));
    }
}
