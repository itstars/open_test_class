package com.scb.test.modular.system.service;

import com.scb.test.modular.system.entity.SysUser;

import java.util.List;

public interface SysUserService {
    SysUser addUser(SysUser user);
    SysUser findById(Integer id);
    List<SysUser> findAll();
    SysUser findByUsername(String username);
    void deleteUser(Integer id);
}
