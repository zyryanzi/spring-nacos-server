package cloud.spring.my.springsecurityclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@OAuth2ClientSecurityMarker
public class OAuth2ClientConfig {

    @Bean
    public SecurityFilterChain oAuth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // 其他请求都需要认证
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                // session 会话管理
                .sessionManagement(sessionManagementConfigure -> sessionManagementConfigure.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                // 代替原来的 @EnableOAuth2Sso
                .oauth2Login(Customizer.withDefaults())
                // 代替原来的 @EnableOAuth2Client
                .oauth2Client(Customizer.withDefaults());
        return http.build();
    }

}
