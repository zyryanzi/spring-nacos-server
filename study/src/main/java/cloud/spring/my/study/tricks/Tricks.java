package cloud.spring.my.study.tricks;

public class Tricks {

    public Tricks() {
        super();
    }

    private String finallyOrNot() {
//        String str = "hello";
        Owner me = new Owner();
        me.setName("hello");
        try {
            return me.getName();
        } finally {
            me.setName("uray");
//            str = "uray";
        }
    }

    public static void main(String[] args) {
//        Tricks tricks = new Tricks();
//        System.out.println(tricks.finallyOrNot());
        System.out.println(1 << 1);
    }

}

class Owner {
    private String name;
    private int age;

    public Owner() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
