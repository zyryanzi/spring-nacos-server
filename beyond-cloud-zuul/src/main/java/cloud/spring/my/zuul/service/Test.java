package cloud.spring.my.zuul.service;

import java.util.ArrayList;

/**
 * @ClassName: Test
 * @Author: uray
 * @Date: 2022-02-25 14:30
 **/
public class Test {

    abstract class Weapon {
        String wType;
        public abstract void action();
    }

    class Knife extends Weapon{
        Knife(){
            super.wType = "knife";
        }
        @Override
        public void action() {
            System.out.println("Perform knife attack");
        }
    }
    class Revolver extends Weapon{
        Revolver(){
            super.wType = "revolver";
        }
        @Override
        public void action() {
            System.out.println("Perform revolver attack");
        }
    }
    class PlasmaGun extends Weapon{
        PlasmaGun(){
            super.wType = "Plasma_Gun";
        }
        @Override
        public void action() {
            System.out.println("Perform Plasma_Gun attack");
        }
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("11");
        TestService ts = new TestService(list);
        ArrayList list2  = new ArrayList();
        list2.add("22");
        TestService ts2 = new TestService(list2);
        System.out.println(ts.getData());
        System.out.println(ts2.getData());

    }

}
