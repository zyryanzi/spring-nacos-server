package cloud.spring.my.spring;

import cloud.spring.my.spring.annotation.Autowired;
import cloud.spring.my.spring.annotation.Component;
import cloud.spring.my.spring.annotation.ComponentScan;
import cloud.spring.my.spring.annotation.Scope;
import cloud.spring.my.spring.interfaces.BeanNameAware;
import cloud.spring.my.spring.interfaces.BeanPostProcessor;
import cloud.spring.my.spring.interfaces.InitializingBean;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UrayApplicationContext {

    private Class configClass;

    /**
     * 单例池
     */
    private final ConcurrentHashMap<String, Object> singletonPool = new ConcurrentHashMap<>();

    /**
     * bean定义 map
     */
    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * beanPostProcessor 列表
     */
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    /**
     * 容器构造器
     * @param configClass
     * @throws ClassNotFoundException
     */
    public UrayApplicationContext(Class configClass) throws ClassNotFoundException {
        this.configClass = configClass;

        // 解析配置累
        // ComponentScan 注解 ---> 扫描路径 ---> 扫描

        // 扫描 并 取得beanDefinition
        scan(configClass);

        // 初始化单例bean
        Iterator<String> beanNameIterator = beanDefinitionMap.keys().asIterator();
        while (beanNameIterator.hasNext()) {
            String beanName = beanNameIterator.next();
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonPool.put(beanName, bean);
            }
        }
    }

    /**
     * 创建 bean
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();

        Object instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(instance, bean);
                }
            }
            // Aware 回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            // beanPostProcessor 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }

            // 初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

            // beanPostProcessor 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 扫描 componentScan, component 注解 获得bean定义
     *
     * @param configClass
     * @throws ClassNotFoundException
     */
    private void scan(Class configClass) throws ClassNotFoundException {
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        path = path.replaceAll("\\.", File.separator);

        // 扫描
        ClassLoader classLoader = UrayApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        assert resource != null;
        File file = new File(resource.getFile());
        scanFileR(classLoader, file);
    }

    /**
     * 递归扫描路径下文件
     *
     * @param classLoader
     * @param file
     */
    private void scanFileR(ClassLoader classLoader, File file) throws ClassNotFoundException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                scanFileR(classLoader, f);
            }
        } else if (file.getName().endsWith(".class")){
            genContents(classLoader, file);
        }
    }

    /**
     * 生成Spring有用的bean资源
     * @param classLoader
     * @param f
     * @throws ClassNotFoundException
     */
    private void genContents(ClassLoader classLoader, File f) throws ClassNotFoundException {
        String packageName = f.getAbsolutePath();
        packageName = packageName.substring(packageName.indexOf("cloud"), packageName.indexOf(".class")).replaceAll(File.separator, ".");
        try {
            Class<?> clazz = classLoader.loadClass(packageName);
            if (clazz.isAnnotationPresent(Component.class)) {
                // 当前类是一个bean ， 判断单例/原型, BeanDefinition

                // 封装后置处理器
                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                    BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                    beanPostProcessorList.add(instance);
                }

                // 取得加了component注解的类的名称
                Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                String beanName = componentAnnotation.value();
                System.out.println("--->>> beanName: " + beanName);

                // bean 定义
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setClazz(clazz);
                // 根据Scope注解判断是否单例bean
                if (clazz.isAnnotationPresent(Scope.class)) {
                    beanDefinition.setScope(clazz.getDeclaredAnnotation(Scope.class).value());
                } else {
                    beanDefinition.setScope("singleton");
                }
                beanDefinitionMap.put(beanName, beanDefinition);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据名称取得 bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                return singletonPool.get(beanName);
            } else {
                // 创建 bean 对象
                return createBean(beanName, beanDefinitionMap.get(beanName));
            }
        } else {
            throw new NullPointerException("不存在对应的bean");
        }
    }


}
