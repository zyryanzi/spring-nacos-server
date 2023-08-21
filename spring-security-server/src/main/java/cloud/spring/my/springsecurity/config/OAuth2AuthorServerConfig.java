package cloud.spring.my.springsecurity.config;

import cloud.spring.my.springsecurity.handle.OAuth2SuccessHandler;
import cloud.spring.my.springsecurity.handle.Oauth2FailureHandler;
import cloud.spring.my.springsecurity.service.IUserService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Configuration
public class OAuth2AuthorServerConfig {

    @Autowired
    private IUserService userService;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        /* Spring Authorization Server 最佳实践 */

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // 自定义用户映射器
        Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper = context -> {
            OidcUserInfoAuthenticationToken oidcUserInfoAuthenticationToken = context.getAuthentication();
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) oidcUserInfoAuthenticationToken.getPrincipal();
            OAuth2AccessToken accessToken = context.getAccessToken();

            Map<String, Object> userInfoMap = userService.getUserInfoMap(jwtAuthenticationToken.getName());
            userInfoMap.put("url", "https://github.com/ITLab1024");
            userInfoMap.put("accessToken", accessToken);
            userInfoMap.put("sub", context.getAuthorization().getPrincipalName());
            return new OidcUserInfo(userInfoMap);
        };

        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                //设置客户端授权中失败的handler处理
                .clientAuthentication(auth -> auth.errorResponseHandler(new Oauth2FailureHandler()))
                //token 相关配置 如  /oauth2/token接口
                .tokenEndpoint(token -> token.errorResponseHandler(new Oauth2FailureHandler()))
                // Enable OpenID Connect 1.0
//                .oidc(Customizer.withDefaults());
                .oidc(oidc -> {
                    oidc.userInfoEndpoint(userInfo -> {
                        userInfo.userInfoMapper(userInfoMapper);
                        userInfo.userInfoResponseHandler(new OAuth2SuccessHandler());
                    });
                });

        http.exceptionHandling(ex ->
                        ex.defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        ))
                // Accept access tokens for User Info and/or Client Registration
                .oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults()));

        return http.build();
    }

//    @Bean
//    public CustomerUserDetailsManager customerUserDetailsManager(DataSource dataSource) {
//        return new CustomerUserDetailsManager(dataSource);
//    }

    /**
     * 注册客户端
     *
     * @param jdbcTemplate
     * @return
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * 授权
     *
     * @param jdbcTemplate
     * @param registeredClientRepository
     * @return
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
                                                           RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
                                                                         RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 默认发放令牌
     *
     * @return
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
        return AuthorizationServerSettings.builder().build();
    }

    /* 以下增加一个自定义header和claim */

    @Bean
    public JwtEncoder jwtEncoder(JWKSource jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean("jwtCustomizer")
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            JwsHeader.Builder headers = context.getJwsHeader();
            JwtClaimsSet.Builder claims = context.getClaims();
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                // Customize headers/claims for access_token
                headers.header("customerHeader", "这是一个自定义header");
                claims.claim("customerClaim", "这是一个自定义Claim");
            } else if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
                // Customize headers/claims for id_token
            }
        };
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator(JwtEncoder jwtEncoder,
                                                  @Qualifier("jwtCustomizer") OAuth2TokenCustomizer jwtCustomizer) {
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(jwtCustomizer);
        OAuth2AccessTokenGenerator oAuth2AccessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator oAuth2RefreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, oAuth2AccessTokenGenerator, oAuth2RefreshTokenGenerator);
    }

    /**
     * 生成 RSA 密钥对，长度 2048
     *
     * @return
     */
    private KeyPair generateRsaKey() {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2 << 10);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        return generator.generateKeyPair();
    }
}
