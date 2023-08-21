package cloud.spring.my.springsecurity.config;

import cloud.spring.my.springsecurity.filter.MyFilter;
import cloud.spring.my.springsecurity.handle.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * spring 官网的示例
 */
//@Configuration
@Deprecated
public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    private boolean flag;

    /**
     * any method that adds another configurer
     * must be done in the init method
     *
     * @param
     * @throws Exception
     */
    @Override
    public void init(HttpSecurity http) throws Exception {
        // 启用跨域
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 框架的 session 创建策略
        http
                .exceptionHandling(eh -> eh.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(scp -> scp.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(ahr ->
                        // 匹配策略
                        ahr
                                .requestMatchers("/login", "/captchaImage").anonymous()
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/preview/**",
                                        "/*.html",
                                        "/**/*.html",
                                        "/**/*.css",
                                        "/**/*.js",
                                        "/auth/**"
                                ).permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/**").hasRole("USER"));

        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        MyFilter myFilter = context.getBean(MyFilter.class);

        // 注入自定义过滤器
        http
                .addFilterBefore(myFilter, MyFilter.class)
                .addFilterAfter(myFilter, MyFilter.class);

    }

    public CustomDsl flag(boolean value) {
        this.flag = value;
        return this;
    }

    public static CustomDsl customDsl() {
        return new CustomDsl();
    }

}
