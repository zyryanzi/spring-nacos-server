package cloud.spring.my.study.gof23.principles.single_responsibility;

/**
 *
 * 多种交通工具共用一个执行方法，执行方法违背了单一职责
 *
 */
public class WrongSingleResponsibility {

    public static void main(String[] args) {
        Transportation transportation = new Transportation();
        transportation.exec("汽车");
        transportation.exec("飞机");
        transportation.exec("火箭");
    }

}

class Transportation {
    public void exec(String name) {
        System.out.println(name + "在地上跑");
    }
}
