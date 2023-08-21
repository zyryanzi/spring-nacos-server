package cloud.spring.my.study.gof23.creationalPatten.factory.method;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.NioCar;

public class NioFactory implements ICarMethodFactory {
    @Override
    public ICar require() {
        return new NioCar();
    }
}
