package com.scb.test.modular.teacher.controller;

import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.teacher.entity.Course;
import com.scb.test.modular.teacher.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 课程控制器
 * @Author zhangheng
 */

@Controller
@RequestMapping("teacher")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * @Description 新增课程页跳转
     * @Author hzhang
     **/
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("roleList", Arrays.asList(SysUser.Role.values()));
        return "teacher/add";
    }

    /**
     * @Description 新增课程
     * @Author hzhang
     **/
    @PostMapping("addItem")
    public String addItem(Course cource){
        courseService.addCourse(cource);
        return "redirect:list";
    }

    /**
     * @Description 课程查询
     * @Author hzhang
     **/
    @GetMapping("list")
    public String list(Model model){
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList",courseList);
        return "teacher/list";
    }

    /**
     * @Description 课程详情
     * @Author hzhang
     **/
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Course course = courseService.findById(id);
        model.addAttribute("course",course);
        return "teacher/edit";
    }

    /**
     * @Description 编辑课程
     * @Author hzhang
     **/
    @PostMapping("editItem")
    public String edit(Course course){
        courseService.updateCourse(course);
        return "redirect:list";
    }

    /**
     * @Description 删除课程
     * @Author hzhang
     **/
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        courseService.deleteCource(id);
        return "redirect:/teacher/list";
    }
}
