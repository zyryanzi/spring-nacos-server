package cloud.spring.my.common.utils;

import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 当前时间
     * @return
     */
    public static Date nowDate() {
        return new Date();
    }

    /**
     * 时间毫秒
     * @return
     */
    public static long nowMillis() {
        return new Date().getTime();
    }
}
