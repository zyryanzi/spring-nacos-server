package cloud.spring.my.study.tricks;

public class FatherAndSon {
    public static void main(String[] args) {
        Father fs = new Son();
        fs.sayName();
    }
}

class Father {

    private String name = "father";

    public Father() {
        name = "father 2.0";
        sayName();
    }

    public void sayName() {
        System.out.println(name);
    }
}

class Son extends Father {

    private String name = "son";

    public Son() {
        this.name = "son 2.0";
        sayName();
    }

    @Override
    public void sayName() {
        System.out.println(name);
    }
}
