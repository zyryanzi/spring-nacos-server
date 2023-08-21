package cloud.spring.my.study.gof23.creationalPatten.factory.method;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.TeslaCar;

public class TeslaFactory implements ICarMethodFactory {

    @Override
    public ICar require() {
        return new TeslaCar();
    }

}
