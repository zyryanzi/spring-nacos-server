package cloud.spring.my.springsecurity.service.impl;

import cloud.spring.my.common.entity.SysUserDetail;
import cloud.spring.my.springsecurity.mapper.SysUserMapper;
import cloud.spring.my.springsecurity.service.IUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    @Resource
    private SysUserMapper userMapper;
    @Autowired

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        SysUserDetail userDetail = userMapper.selectUserDetailsByName(username);
        if (ObjectUtils.isEmpty(userDetail)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (CollectionUtils.isEmpty(userDetail.getRoles())) {
            throw new UsernameNotFoundException("角色信息异常");
        }

        Collection<GrantedAuthority> authorities = userDetail.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleKey()))
                .collect(Collectors.toList());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                userDetail.getUsername(),
                userDetail.getPassword(),
                authorities);

        return userDetails;
    }

    /**
     * 自定义用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public Map<String, Object> getUserInfoMap(String username) throws UsernameNotFoundException {
        Map<String, Object> userInfoMap = userMapper.selectUserInfoMap(username);
        return userInfoMap;
    }
}
