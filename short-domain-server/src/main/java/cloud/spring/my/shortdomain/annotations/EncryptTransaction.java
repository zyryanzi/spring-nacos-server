package cloud.spring.my.shortdomain.annotations;

import java.lang.annotation.*;

/**
 * 加密注解
 *
 * 该注解有两种使用方式
 * ①：配合@SensitiveData加在类中的字段上
 * ②：直接在Mapper中的方法参数上使用
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface EncryptTransaction {
}
