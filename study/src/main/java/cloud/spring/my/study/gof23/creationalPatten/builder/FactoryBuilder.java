package cloud.spring.my.study.gof23.creationalPatten.builder;

import cloud.spring.my.study.domain.Factory;

/**
 * 建造者模式
 */
public abstract class FactoryBuilder {

    public abstract FactoryBuilder name(String name);

    /**
     * 地基
     */
    public abstract FactoryBuilder foundation(String foundation);

    /**
     * 框架
     */
    public abstract FactoryBuilder frame(String frame);

    /**
     * 装修
     */
    public abstract FactoryBuilder renovation(String renovation);

    /**
     * 建造
     */
    public abstract Factory build();

}
