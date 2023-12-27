package cloud.spring.my.study.gof23.principles.riskov;

/**
 * 利是替换
 * 子类应尽量不要去重写父类的方法，否则子类将在引用父类该方法的地方变得不透明
 * 也就是说所有父类的引用对象 替换为子类的引用对象时，不应产生任何影响；
 * 这里违反了该规则
 */
public class WrongRiskov {

    public static void main(String[] args) {
        Father obj = new Father();
        System.out.println("10 + 4= " + obj.func1(10, 4));

        // 这里将父类的引用替换为子类的引用，得到的结果和原来不同，超出预期
        obj = new Son();
        System.out.println("10 + 4= " + obj.func1(10, 4));
    }

}

class Father {
    public int func1(int a, int b) {
        return a + b;
    }

    public String func2() {
        return "方法2";
    }
}

class Son extends Father {

    @Override
    public int func1(int a, int b) {
        return a - b;
    }
}
