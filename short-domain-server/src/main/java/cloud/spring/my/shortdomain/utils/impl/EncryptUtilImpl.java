package cloud.spring.my.shortdomain.utils.impl;

import cloud.spring.my.shortdomain.annotations.EncryptTransaction;
import cloud.spring.my.shortdomain.utils.DBAESUtil;
import cloud.spring.my.shortdomain.utils.IEncryptUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class EncryptUtilImpl implements IEncryptUtil {

    @Override
    public <T> T encrypt(Field[] declaredFields, T paramObject) throws IllegalAccessException {
        //取出所有被EncryptTransaction注解的字段
        for (Field field : declaredFields) {
            EncryptTransaction encryptTransaction = field.getAnnotation(EncryptTransaction.class);
            if (!Objects.isNull(encryptTransaction)) {
                field.setAccessible(true);
                Object object = field.get(paramObject);
                //暂时只实现String类型的加密
                if (object instanceof String value) {
                    //加密
                    try {
                        // todo 需要关注的加密逻辑
                        field.set(paramObject, DBAESUtil.encrypt(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return paramObject;
    }
}
