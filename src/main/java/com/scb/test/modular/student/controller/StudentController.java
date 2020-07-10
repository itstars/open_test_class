package com.scb.test.modular.student.controller;

import com.scb.test.common.exception.BusinessException;
import com.scb.test.modular.student.service.StudentService;
import com.scb.test.modular.teacher.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * @Description 学生选课控制器
 * @Author zhangheng
 */

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * @Description 学生订课列表查询
     * @Author hzhang
     **/
    @GetMapping("list")
    public String list(Model model){
        List<Course> courseList = studentService.findCourse();
        List<Course> myCourseList = studentService.findOrderCourse();
        model.addAttribute("courseList",courseList);
        model.addAttribute("myCourseList",myCourseList);
        return "student/list";
    }

    /**
     * @Description 学生订课
     * @Author hzhang
     **/
    @GetMapping("order/{id}")
    public String orderCourse(@PathVariable("id") Integer id){
        studentService.orderCourse(id);
        return "redirect:/student/list";
    }

    /**
     * @Description 学生取消订课
     * @Author hzhang
     **/
    @GetMapping("cancel/{id}")
    public String delete(@PathVariable("id") Integer id){
        studentService.cancelCource(id);
        return "redirect:/student/list";
    }
}
