package cloud.spring.my.study.domain;

public class TeslaCar implements ICar {

    @Override
    public void manufacture() {
        System.out.println("生产一台特斯拉");
    }

    @Override
    public void paint() {
        System.out.println("涂装一台特斯拉");
    }

}
