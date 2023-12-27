package cloud.spring.my.study.domain;

//import jakarta.servlet.annotation.WebFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.core.annotation.Order;

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


//@WebFilter
public class Car extends Product implements Cloneable {

    /**
     * 品牌通过组合的方式实现桥接模式
     */
    private Brand brand;

    public Car(Brand brand) {
        super(brand);
        this.brand = brand;
    }

    @Override
    public void manufacture() {
        System.out.println("制作一辆汽车");
    }

    @Override
    public void paint() {
        System.out.println("喷涂一辆汽车");
    }

    @Override
    public void info() {
        super.info();
        System.out.println("汽车");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        int i = 31;
        System.out.println();
        NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
    }

}
