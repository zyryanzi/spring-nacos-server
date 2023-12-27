package cloud.spring.my.study.gof23.principles.interface_segregation;

/**
 * 接口隔离
 * 通过拆分 InterfaceI -> Interface1I Interface1II Interface1III
 * 确保实现类没有实现多余的方法，实现了接口隔离
 */
public class ImprovedInterfaceSegregation {

    public static void main(String[] args) {
        A1 a = new A1();
        a.execMethod1();
        a.execMethod2();
        a.execMethod3();
        B1 b = new B1();
        b.execMethod1();
        b.execMethod4();
        b.execMethod5();
    }

}

interface Interface1I {
    void method1();
}

interface Interface1II {
    void method2();

    void method3();
}

interface Interface1III {
    void method4();

    void method5();
}

class C1 implements Interface1I, Interface1II {
    @Override
    public void method1() {
        System.out.println("C 执行 method1 ");
    }

    @Override
    public void method2() {
        System.out.println("C 执行 method2 ");
    }

    @Override
    public void method3() {
        System.out.println("C 执行 method3 ");
    }
}

class D1 implements Interface1I, Interface1III {
    @Override
    public void method1() {
        System.out.println("D 执行 method1 ");
    }

    @Override
    public void method4() {
        System.out.println("D 执行 method4 ");
    }

    @Override
    public void method5() {
        System.out.println("D 执行 method5 ");
    }
}

class A1 {
    private C1 c = new C1();

    public void execMethod1() {
        c.method1();
    }

    public void execMethod2() {
        c.method2();
    }

    public void execMethod3() {
        c.method3();
    }
}

class B1 {
    private D1 d = new D1();

    public void execMethod1() {
        d.method1();
    }

    public void execMethod4() {
        d.method4();
    }

    public void execMethod5() {
        d.method5();
    }
}
