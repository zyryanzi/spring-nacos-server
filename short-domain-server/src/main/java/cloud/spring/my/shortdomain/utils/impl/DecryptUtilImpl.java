package cloud.spring.my.shortdomain.utils.impl;

import cloud.spring.my.shortdomain.annotations.EncryptTransaction;
import cloud.spring.my.shortdomain.utils.DBAESUtil;
import cloud.spring.my.shortdomain.utils.IDecryptUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class DecryptUtilImpl implements IDecryptUtil {

    @Override
    public <T> T decrypt(T result) throws IllegalAccessException {
        //取出resultType的类
        Class<?> resultClass = result.getClass();
        Field[] declaredFields = resultClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //取出所有被DecryptTransaction注解的字段
            EncryptTransaction encryptTransaction = field.getAnnotation(EncryptTransaction.class);
            if (!Objects.isNull(encryptTransaction)) {
                field.setAccessible(true);
                Object object = field.get(result);
                //String的解密
                if (object instanceof String value) {
                    //对注解的字段进行逐一解密
                    try {
                        field.set(result, DBAESUtil.decrypt(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
