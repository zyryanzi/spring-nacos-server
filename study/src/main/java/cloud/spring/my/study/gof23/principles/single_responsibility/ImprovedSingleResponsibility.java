package cloud.spring.my.study.gof23.principles.single_responsibility;

/**
 * 每种交通工具又一个独立的执行方法，实现了方法层面的单一职责
 */
public class ImprovedSingleResponsibility {

    public static void main(String[] args) {
        Transportation2 transportation = new Transportation2();
        transportation.vehicleExec("汽车");
        transportation.planeExec("飞机");
        transportation.rocketExec("火箭");
    }

}

class Transportation2 {
    public void vehicleExec(String name) {
        System.out.println(name + "在地上跑");
    }

    public void planeExec(String name) {
        System.out.println(name + "在天上飞");
    }

    public void rocketExec(String name) {
        System.out.println(name + "飞向宇宙");
    }
}
