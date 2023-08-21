package cloud.spring.my.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {

    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;

}
