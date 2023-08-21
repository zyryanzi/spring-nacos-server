package cloud.spring.my.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Author: uray
 * @Date: 2022-03-04 15:35
 **/
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public class SysRole extends BaseEntity {

    private Long id;
    private String roleName;
    private String roleKey;
    private String dataScope;
    private Integer status;
    private String remark;

}
