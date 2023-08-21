package cloud.spring.my.study.gof23.creationalPatten.factory.abstractt;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.IRocket;

/**
 * 抽象工厂模式
 * 工厂的工厂
 */
public interface IProductFactory {

    /**
     * 要求car
     * @return
     */
    ICar requireCar();

    /**
     * 要求rocket
     * @return
     */
    IRocket requireRocket();

}
