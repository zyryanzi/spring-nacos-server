package cloud.spring.my.study.gof23.creationalPatten.builder;

import cloud.spring.my.study.domain.Factory;

/**
 * 当建造当对象需要客户自定义，则不需要director
 */
public class Director {

    public Factory build(FactoryBuilder factoryBuilder) {
//        factoryBuilder.foundation();
//        factoryBuilder.frame();
//        factoryBuilder.renovation();

        return factoryBuilder.build();
    }

}
