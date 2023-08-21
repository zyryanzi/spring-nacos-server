package cloud.spring.my.study.gof23.creationalPatten.singleton;

import lombok.NoArgsConstructor;

/**
 * 内部类单例
 */
@NoArgsConstructor
public class SICSingleton {

    private static class Inner {
        private static final SICSingleton IC_SINGLETON = new SICSingleton();
    }

    public static SICSingleton getInstance() {
        return Inner.IC_SINGLETON;
    }

}
