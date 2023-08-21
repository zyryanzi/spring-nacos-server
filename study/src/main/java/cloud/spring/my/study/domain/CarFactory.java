package cloud.spring.my.study.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CarFactory extends Factory{

    /**
     * 轮胎检测
     */
    private String wheelTest;

    /**
     * 车机服务提供商
     */
    private String tsp;

}

