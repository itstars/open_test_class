package com.scb.test.modular.system.repo;

import com.scb.test.modular.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 系统用户Dao
 * @Author zhangheng
 */
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {
    SysUser findByUsername(String username);
}
