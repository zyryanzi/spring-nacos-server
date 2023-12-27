package cloud.spring.my.shortdomain.interceptor;

import cloud.spring.my.shortdomain.annotations.SensitiveData;
import cloud.spring.my.shortdomain.utils.IDecryptUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * Mybatis 结果集拦截器
 *
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class ResultSetInterceptor implements Interceptor {

    @Resource
    private IDecryptUtil decryptUtil;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 取出查询结果
        Object resultObj = invocation.proceed();
        if (ObjectUtils.isEmpty(resultObj)) {
            return null;
        }
        if (resultObj instanceof ArrayList<?> resultList) {
            // 基于selectList
            if (this.needToDecrypt(resultList.get(0))) {
                for (Object result : resultList) {
                    decryptUtil.decrypt(result);
                }
            }
        } else {
            // 基于selectOne
            if (needToDecrypt(resultObj)) {
                decryptUtil.decrypt(resultObj);
            }
        }
        return resultObj;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 判断是否需要解密
     *
     * @param object
     * @return
     */
    private boolean needToDecrypt(Object object) {
        Class<?> objectClass = object.getClass();
        SensitiveData sensitiveData = AnnotationUtils.findAnnotation(objectClass, SensitiveData.class);
        return Objects.nonNull(sensitiveData);
    }
}
