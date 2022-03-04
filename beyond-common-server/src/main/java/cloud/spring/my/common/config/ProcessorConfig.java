package cloud.spring.my.common.config;

import cloud.spring.my.common.processor.IGenIdServicePostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ProcessorConfig
 * @Author: uray
 * @Date: 2022-03-04 13:19
 **/
@Configuration
public class ProcessorConfig {

    /**
     * 注入对应的 IGenIdServicePostProcessor 对象内容;
     * 当服务启动之后会调用 IGenIdServicePostProcessor 的 postProcessAfterInitialization 方法
     * 根据我们设置的条件拦截那些需要做对应的方法增强使用
     * @param
     * @create: 2022/3/4 13:20
     * @return: cloud.spring.my.common.processor.IGenIdServicePostProcessor
     * @auther: uray
     */ 
    @Bean
    public IGenIdServicePostProcessor initIGenIdServicePostProcessor(){
        IGenIdServicePostProcessor processor = new IGenIdServicePostProcessor();
        return processor;
    }

}
