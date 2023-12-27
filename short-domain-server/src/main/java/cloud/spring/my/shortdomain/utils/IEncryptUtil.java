package cloud.spring.my.shortdomain.utils;

import java.lang.reflect.Field;

/**
 * 加密工具接口
 *
 */
public interface IEncryptUtil {

    /**
     * 加密
     *
     * @param declaredFields 加密字段
     * @param paramObject    对象
     * @param <T>            入参类型
     * @return 加密后的对象
     * @throws IllegalAccessException 访问权限异常
     */
    <T> T encrypt(Field[] declaredFields, T paramObject) throws IllegalAccessException;

}
