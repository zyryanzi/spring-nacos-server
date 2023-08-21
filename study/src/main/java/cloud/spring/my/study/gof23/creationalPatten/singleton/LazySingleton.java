package cloud.spring.my.study.gof23.creationalPatten.singleton;

/**
 * 懒汉式
 */
public class LazySingleton {

    // 一些资源
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];

    // 先不new 即不浪费空间资源
    // volatile 避免指令重排 确保原子性
    private static volatile LazySingleton LAZY_SINGLETON;

    private LazySingleton() {
        synchronized (LazySingleton.class) {
            if (LAZY_SINGLETON != null) {
                // 防止意外操作破坏单例，刻意操作仍可以破坏，枚举单例才最安全
                throw new RuntimeException("您正在破坏单例模式");
            }
        }
    }

    /**
     * DCL 懒汉式
     *
     * @return
     */
    public static LazySingleton getInstance() {
        if (LAZY_SINGLETON == null) {
            synchronized (LazySingleton.class) {
                if (LAZY_SINGLETON == null) {
                    /*
                        非原子操作：
                        1 分配堆内存；
                        2 创建对象；
                        3 将对象指向 LAZY_SINGLETON
                        a线程 13-------2
                        b线程进来时 空间已指向 LAZY_SINGLETON, 即不是null, 就会直接返回 "虚无的LAZY_SINGLETON"
                     */
                    LAZY_SINGLETON = new LazySingleton();
                }
            }
        }
        return LAZY_SINGLETON;
    }

}
