package cloud.spring.my.study.gof23.creationalPatten.factory.abstractt;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.IRocket;
import cloud.spring.my.study.domain.TeslaCar;
import cloud.spring.my.study.domain.TeslaRocket;

public class TeslaFactory implements IProductFactory{
    @Override
    public ICar requireCar() {
        return new TeslaCar();
    }

    @Override
    public IRocket requireRocket() {
        return new TeslaRocket();
    }
}
