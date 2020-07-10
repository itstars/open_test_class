package com.scb.test.common.config;

import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description 用户权限获取
 * @Author zhangheng
 */

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByUsername(username);
        if(null == sysUser){
            throw new UsernameNotFoundException("user not found");
        }
        sysUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" +sysUser.getRole().name()));
        return sysUser;
    }
}
