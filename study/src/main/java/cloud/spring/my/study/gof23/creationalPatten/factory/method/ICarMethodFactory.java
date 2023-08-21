package cloud.spring.my.study.gof23.creationalPatten.factory.method;

import cloud.spring.my.study.domain.ICar;

/**
 * 工厂方法模式
 */
public interface ICarMethodFactory {

    /**
     * 要求car
     * @return
     */
    ICar require();

}
