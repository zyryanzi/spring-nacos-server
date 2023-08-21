package cloud.spring.my.study.domain;

public interface ICar {

    /**
     * 装配
     */
    void manufacture() throws NoSuchMethodException;

    /**
     * 涂装
     */
    void paint() throws NoSuchMethodException;

}
