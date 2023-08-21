package cloud.spring.my.study.gof23.creationalPatten.builder;

import cloud.spring.my.study.domain.CarFactory;
import lombok.Data;

@Data
public class CarFactoryWorker extends FactoryBuilder {

    private CarFactory carFactory;

    public CarFactoryWorker() {
        carFactory = new CarFactory();
    }

    @Override
    public CarFactoryWorker name(String name) {
        carFactory.setName("特斯拉车工厂");
        return this;
    }

    @Override
    public CarFactoryWorker foundation(String foundation) {
        carFactory.setFoundation(foundation);
        return this;
    }

    @Override
    public CarFactoryWorker frame(String frame) {
        carFactory.setFrame(frame);
        return this;
    }

    @Override
    public CarFactoryWorker renovation(String renovation) {
        carFactory.setRenovation(renovation);
        return this;
    }

    public CarFactoryWorker wheelTest(String wheelTest) {
        carFactory.setWheelTest(wheelTest);
        return this;
    }

    public CarFactoryWorker tsp(String tsp) {
        carFactory.setTsp(tsp);
        return this;
    }

    @Override
    public CarFactory build() {
        return carFactory;
    }
}
