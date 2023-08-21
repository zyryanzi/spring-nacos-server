package cloud.spring.my.study.domain;

import lombok.Data;

@Data
public class Tesla implements Brand{

    private final String name = "TESLA";

    @Override
    public void info() {
        System.out.print(name);
    }

}
