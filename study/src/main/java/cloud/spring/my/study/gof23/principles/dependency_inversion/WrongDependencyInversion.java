package cloud.spring.my.study.gof23.principles.dependency_inversion;

/**
 * 依赖倒转
 *
 * person 类依赖了具体的 email 类，而不是依赖抽象，违反了依赖倒转的原则，
 * 导致代码失去了易扩展性
 */
public class WrongDependencyInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

class Person {
    public void receive(Email email) {
        System.out.println(email.execReceive());
    }
}

class Email {
    public String execReceive() {
        return "接收电子邮件";
    }
}
