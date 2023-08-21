package cloud.spring.my.study.gof23.creationalPatten.factory.abstractt;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.IRocket;
import cloud.spring.my.study.domain.NioCar;
import cloud.spring.my.study.domain.NioRocket;

public class NioFactory implements IProductFactory{
    @Override
    public ICar requireCar() {
        return new NioCar();
    }

    @Override
    public IRocket requireRocket() {
        return new NioRocket();
    }
}
