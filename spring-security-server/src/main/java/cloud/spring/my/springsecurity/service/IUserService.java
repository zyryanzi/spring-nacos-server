package cloud.spring.my.springsecurity.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

public interface IUserService {

    /**
     * 自定义用户信息映射
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    Map<String, Object> getUserInfoMap(String username) throws UsernameNotFoundException;

}
