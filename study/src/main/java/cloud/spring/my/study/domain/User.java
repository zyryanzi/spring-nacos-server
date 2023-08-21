package cloud.spring.my.study.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private Long userId;

    private String username;

    private String password;

}
