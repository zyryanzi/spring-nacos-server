package cloud.spring.my.springsecurity.mapper;

import cloud.spring.my.common.entity.SysUserDetail;

import java.util.Map;

public interface SysUserMapper {

    SysUserDetail selectUserDetailsByName(String name);

    Map<String, Object> selectUserInfoMap(String username);
}
