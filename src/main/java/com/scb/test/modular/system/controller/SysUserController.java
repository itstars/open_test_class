package com.scb.test.modular.system.controller;

import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 系统用户控制器
 * @Author zhangheng
 */

@Controller
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @Description 添加用户页跳转
     * @Author hzhang
     **/
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("roleList", Arrays.asList(SysUser.Role.values()));
        return "sysuser/add";
    }

    /**
     * @Description 添加用户
     * @Author hzhang
     **/
    @PostMapping("addItem")
    public String addItem(SysUser user){
        sysUserService.addUser(user);
        return "redirect:list";
    }

    /**
     * @Description 用户列表查询
     * @Author hzhang
     **/
    @GetMapping("list")
    public String list(Model model){
        List<SysUser> users = sysUserService.findAll();
        model.addAttribute("userList",users);
        return "sysuser/list";
    }

    /**
     * @Description 用户详情查询
     * @Author hzhang
     **/
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id,Model model){
        SysUser sysUser = sysUserService.findById(id);
        model.addAttribute("user",sysUser);
        model.addAttribute("roleList", Arrays.asList(SysUser.Role.values()));
        return "sysuser/edit";
    }

    /**
     * @Description 修改用户
     * @Author hzhang
     **/
    @PostMapping("editItem")
    public String edit(SysUser user){
        sysUserService.addUser(user);
        return "redirect:list";
    }

    /**
     * @Description 删除用户
     * @Author hzhang
     **/
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        sysUserService.deleteUser(id);
        return "redirect:/user/list";
    }
}
