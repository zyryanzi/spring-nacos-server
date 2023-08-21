package cloud.spring.my.study.domain;

public interface IRocket {

    /**
     * 组装
     */
    void manufacture() throws NoSuchMethodException;

    /**
     * 发射
     */
    void launch() throws NoSuchMethodException;

}
