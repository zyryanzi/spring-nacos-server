package cloud.spring.my.study.domain;

import lombok.Data;

@Data
public abstract class Factory {

    /**
     * 工厂名称
     */
    private String name;

    /**
     * 地基处理类型
     */
    private String foundation;

    /**
     * 建筑框架类型
     */
    private String frame;

    /**
     * 装潢类型
     */
    private String renovation;

}
