package cloud.spring.my.study.gof23.creationalPatten.singleton;

import lombok.NoArgsConstructor;

/**
 * 饿汉式 可能浪费内存
 */
@NoArgsConstructor
public class HungrySingleton {

    // 启动就加载，浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];

    private final static HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }

}
