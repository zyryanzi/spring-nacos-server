package cloud.spring.my.study.gof23.principles.dependency_inversion;

/**
 * 依赖倒转
 *
 * person 类依赖了抽象的IReceiver，实现了依赖倒转的原则，
 * 代码具备了易扩展性
 * 通常有：接口传递 构造方法传递 setter方法传递 三种方式进行依赖注入
 */
public class ImprovedDependencyInversion {
    public static void main(String[] args) {
        Person1 person = new Person1();
        person.receive(new Email1());
        person.receive(new Telephone1());
    }
}

interface IReceiver{
    String execReceive();
}

class Person1 {
    public void receive(IReceiver receiver) {
        System.out.println(receiver.execReceive());
    }
}

class Email1 implements IReceiver {
    public String execReceive() {
        return "Email 接收电子邮件";
    }
}

class Telephone1 implements IReceiver {
    public String execReceive() {
        return "Telephone 接收短信息";
    }
}
