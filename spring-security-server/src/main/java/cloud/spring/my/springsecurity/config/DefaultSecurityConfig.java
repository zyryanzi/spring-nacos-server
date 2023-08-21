package cloud.spring.my.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * The code is invoked in the following order:
 * Code in the Config.configure method is invoked
 * Code in the MyCustomDsl.init method is invoked
 * Code in the MyCustomDsl.configure method is invoked
 */

// proxyBeanMethods = false 不代理该类的 bean
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableMethodSecurity
public class DefaultSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // e.g.
//        http.apply(CustomDsl.customDsl()).flag(true);

        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(
                                        new AntPathRequestMatcher("/actuator/**"),
                                        new AntPathRequestMatcher("/oauth2/**"),
                                        new AntPathRequestMatcher("/**/*.json"),
                                        new AntPathRequestMatcher("/**/*.html")
                                )
                                .permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/salesflow"))
                                .hasAuthority("area:manger")
                                .anyRequest()
                                .authenticated()
                )
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
