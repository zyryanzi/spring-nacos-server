package cloud.spring.my.shortdomain.utils;

/**
 * 解密工具接口
 *
 */
public interface IDecryptUtil {

    /**
     * 解密
     *
     * @param result resultType的实例
     * @param <T>    解密结果类型
     * @return 解密结果
     * @throws IllegalAccessException 字段访问权限异常
     */
    <T> T decrypt(T result) throws IllegalAccessException;

}
