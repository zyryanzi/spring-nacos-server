package cloud.spring.my.study.domain;

public class Car extends Product{

    /**
     * 品牌通过组合的方式实现桥接模式
     */
    private Brand brand;

    public Car(Brand brand) {
        super(brand);
        this.brand = brand;
    }

    @Override
    public void manufacture() {
        System.out.println("制作一辆汽车");
    }

    @Override
    public void paint() {
        System.out.println("喷涂一辆汽车");
    }

    @Override
    public void info() {
        super.info();
        System.out.println("汽车");
    }
}
