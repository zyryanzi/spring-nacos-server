package cloud.spring.my.study.gof23.creationalPatten.factory.simple;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.NioCar;
import cloud.spring.my.study.domain.TeslaCar;
import cloud.spring.my.study.enums.CarName;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 静态工厂模式
 */
public class CarFactory {

    /**
     * 指定 car
     * @param name
     * @return
     */
    public static ICar require(String name) {
        ICar car;

        CarName carName = CarName.getByName(name);
        if (ObjectUtils.isEmpty(carName)) {
            System.out.println("不具备该品牌生产能力");
            return null;
        }

        switch (carName) {
            case TESLA:
                car = new TeslaCar();
                break;
            case NIO:
                car = new NioCar();
                break;
            default:
                throw new RuntimeException("汽车工厂异常");
        }

        return car;
    }

}
