package cloud.spring.my.study.gof23.principles.interface_segregation;

/**
 * 接口隔离
 * A、B 通过 C、D 调用接口 InterfaceI 的方法，
 * A 通过 C 调用 3 个，B 通过 D 调用 3 个, 但是 C、D 都实现了接口的全部方法，
 * 违反了接口隔离的原则
 */
public class WrongInterfaceSegregation {

    public static void main(String[] args) {
        A a = new A();
        a.execMethod1();
        a.execMethod2();
        a.execMethod3();
        B b = new B();
        b.execMethod1();
        b.execMethod4();
        b.execMethod5();
    }

}

interface InterfaceI {
    void method1();

    void method2();

    void method3();

    void method4();

    void method5();
}

class C implements InterfaceI {
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

    @Override
    public void method4() {
        System.out.println("C 执行 method4 ");
    }

    @Override
    public void method5() {
        System.out.println("C 执行 method5 ");
    }
}

class D implements InterfaceI {
    @Override
    public void method1() {
        System.out.println("D 执行 method1 ");
    }

    @Override
    public void method2() {
        System.out.println("D 执行 method4 ");
    }

    @Override
    public void method3() {
        System.out.println("D 执行 method5 ");
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

class A {
    private C c = new C();

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

class B {
    private D d = new D();

    public void execMethod1() {
        d.method1();
    }

    public void execMethod4() {
        d.method2();
    }

    public void execMethod5() {
        d.method3();
    }
}
