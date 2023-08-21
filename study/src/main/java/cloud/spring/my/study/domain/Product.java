package cloud.spring.my.study.domain;

public abstract class Product implements ICar, IRocket {

    protected Brand brand;

    public Product(Brand brand) {
        this.brand = brand;
    }

    public void info() {
        brand.info();
    }

    @Override
    public void manufacture() throws NoSuchMethodException {
        throw new NoSuchMethodException("需要由子类实现");
    }

    @Override
    public void launch() throws NoSuchMethodException {
        throw new NoSuchMethodException("需要由子类实现");
    }

    @Override
    public void paint() throws NoSuchMethodException {
        throw new NoSuchMethodException("需要由子类实现");
    }

}
