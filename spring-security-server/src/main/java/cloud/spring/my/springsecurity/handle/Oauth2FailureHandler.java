package cloud.spring.my.springsecurity.handle;

import cloud.spring.my.common.core.text.AjaxResult;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Oauth2FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        exception.printStackTrace();

        String message;

        if (exception instanceof OAuth2AuthenticationException oAuth2AuthenticationException) {
            OAuth2Error error = oAuth2AuthenticationException.getError();
            message = "认证信息异常：" + error.getErrorCode() + "，" + error.getDescription();
        } else {
            message = exception.getMessage();
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        String respJson = JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED.value(), message));
        response.getWriter().write(respJson);
        response.getWriter().flush();

    }

}
