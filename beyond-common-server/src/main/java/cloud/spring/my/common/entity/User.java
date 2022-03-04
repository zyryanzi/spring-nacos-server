package cloud.spring.my.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Author: uray
 * @Date: 2022-03-04 15:35
 **/
@NoArgsConstructor
@Data
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
}
