package cloud.spring.my.study.domain;

public class NioRocket implements IRocket {
    @Override
    public void manufacture() {
        System.out.println("梦想组装一架火箭");
    }

    @Override
    public void launch() {
        System.out.println("梦想中火箭发射升空");
    }
}
