package cloud.spring.my.study.gof23.creationalPatten.singleton;

public class EnumSingleton {

    /**
     * 私有构造器，供枚举类中使用
     */
    private EnumSingleton() {
        System.out.println("调用私有构造器，构造单例");
    }

    /**
     * 静态枚举，可以避免反射和反序列化破坏单例
     */
    public enum SingletonEnum {
        /**
         * 枚举方式的单例，利用枚举本身只有一个实例的特性
         */
        SINGLETON;

        private EnumSingleton instance;
        SingletonEnum() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }

}
