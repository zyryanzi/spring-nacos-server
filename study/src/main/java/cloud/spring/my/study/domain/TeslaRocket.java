package cloud.spring.my.study.domain;

public class TeslaRocket implements IRocket {
    @Override
    public void manufacture() {
        System.out.println("组装一架火箭");
    }

    @Override
    public void launch() {
        System.out.println("火箭发射升空");
    }
}
