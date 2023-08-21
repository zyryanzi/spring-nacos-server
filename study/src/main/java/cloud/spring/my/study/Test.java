package cloud.spring.my.study;

import cloud.spring.my.study.domain.ICar;
import cloud.spring.my.study.domain.NioCar;
import cloud.spring.my.study.gof23.creationalPatten.singleton.LazySingleton;
import cloud.spring.my.study.gof23.structuralPatten.proxy.MyProxyHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Car tesla = CarFactory.require(CarName.TESLA.name());
//        tesla.manufacture();
//        Car nio = CarFactory.require(CarName.NIO.name());
//        nio.manufacture();
//        IProductFactory teslaFactory = new TeslaFactory();
//        ICar teslaCar = teslaFactory.requireCar();
//        teslaCar.manufacture();
//        teslaCar.paint();
//        IRocket teslaRocket = teslaFactory.requireRocket();
//        teslaRocket.manufacture();
//        teslaRocket.launch();
//
//        User user = new User().setUserId(1L).setUsername("马斯克").setPassword("123456");

//        CarFactoryWorker worker = new CarFactoryWorker()
//                .name("特斯拉车工厂")
//                .foundation("地基硬化处理回填技术")
//                .frame("强化钢筋混凝土结构")
//                .renovation("粗糙的工业化装修风格")
//                .wheelTest("一流轮胎检测设备")
//                .tsp("特斯拉车机服务系统");
//
//        CarFactory build = worker.build();
//        System.out.println(build);

//        NioCar nioCar = new NioCar();
//        Charging charging = new MarsCharging();
//        ChargingAdapter adapter = new MarsAdapter((MarsCharging) charging);
//        nioCar.charge(adapter);

//        Car teslaCar = new Car(new Tesla());
//        teslaCar.info();

        ICar nioCar = new NioCar();

        MyProxyHandler mph = new MyProxyHandler();
        mph.setTarget(nioCar);
        ICar proxy = (ICar) mph.getProxy();
        proxy.manufacture();

        Constructor<LazySingleton> declaredConstructor = LazySingleton.class.getDeclaredConstructor();
        LazySingleton lazySingleton = declaredConstructor.newInstance();
    }

}
