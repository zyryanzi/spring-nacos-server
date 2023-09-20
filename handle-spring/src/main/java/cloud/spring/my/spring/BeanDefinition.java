package cloud.spring.my.spring;

/**
 * 对象定义，主要用于区分单例和原型
 */
public class BeanDefinition {

    /**
     * 对象
     */
    private Class clazz;

    /**
     * 类型 单例或原型
     */
    private String scope;

    public BeanDefinition() {
        super();
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.scope = scope;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
