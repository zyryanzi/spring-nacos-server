package cloud.spring.my.springsecurity;

import cloud.spring.my.springsecurity.config.CustomerUserDetailsManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.UUID;

@SpringBootTest
public class SpringSecurityTest {

//    @Autowired
//    private UserDetailsManager userDetailsManager;

    @Autowired
    private DataSource dataSource;

    @Test
    void saveUser() {
        UserDetails userDetails = User.builder().passwordEncoder(s -> new BCryptPasswordEncoder().encode(s))
                .username("uray")
                .password("qwer1234")
                .roles("USER")
                .build();
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setCreateUserSql("insert into sys_user (username, password, enabled, create_by) values (?,?,?,'manu')");
        userDetailsManager.setCreateAuthoritySql("insert into authorities (username, authority, create_by) values (?,?,'manu')");
        userDetailsManager.createUser(userDetails);
    }

    /**
     * 创建clientId信息
     */
    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Test
    void saveClient() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("messaging-client")
                .clientSecret(new BCryptPasswordEncoder().encode("secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:59000/login/oauth2/code/messaging-client-oidc")
                .redirectUri("http://127.0.0.1:59000/authorized")
                .scope(OidcScopes.OPENID).scope("message.read")
                .scope("message.write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        registeredClientRepository.save(registeredClient);
    }

}
