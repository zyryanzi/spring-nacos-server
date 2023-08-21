package cloud.spring.my.study.gof23.creationalPatten.singleton;

/**
 * 使用 ThreadLocal
 * 为每个线程创建一个单例对象
 */
public class TlSingleton {

    private static final ThreadLocal<TlSingleton> tlSingleton = ThreadLocal.withInitial(TlSingleton::new);

    private TlSingleton() {
        System.out.println("调用私有构造器，构造单例");
    }

    public static TlSingleton getInstance(){
        return tlSingleton.get();
    }
}
