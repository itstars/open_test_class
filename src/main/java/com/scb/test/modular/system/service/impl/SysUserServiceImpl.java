package com.scb.test.modular.system.service.impl;

import com.scb.test.common.exception.BusinessException;
import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.system.repo.SysUserRepository;
import com.scb.test.modular.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @Description 用户服务实现
 * @Author zhangheng
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public List<SysUser> findAll(){
        return sysUserRepository.findAll();
    }

    @Override
    public SysUser findById(Integer id){
        return sysUserRepository.findById(id).get();
    }

    @Override
    public SysUser addUser(SysUser user){
        if(StringUtils.isEmpty(user.getUsername())){
            throw new BusinessException("400","用户名不能为空!");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            throw new BusinessException("400","用户密码不能为空!");
        }
        if(StringUtils.isEmpty(user.getRole())){
            throw new BusinessException("400","用户类型不能为空!");
        }
        String password = user.getPassword();
        String encodePwd = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encodePwd);
        return sysUserRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id){
        sysUserRepository.deleteById(id);
    }

    @Override
    public SysUser findByUsername(String username){
        return sysUserRepository.findByUsername(username);
    }
}
