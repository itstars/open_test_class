package com.scb.test.modular.student.service.impl;

import com.scb.test.modular.student.service.CourseOrderService;
import com.scb.test.modular.student.service.StudentService;
import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.teacher.entity.Course;
import com.scb.test.modular.teacher.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 学生服务实现类
 * @Author zhangheng
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseOrderService courseOrderService;


    @Override
    public List<Course> findCourse(){
        return courseService.findCourse();
    }

    @Override
    public List<Course> findOrderCourse() {
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Course> myOrderCourseList = courseOrderService.findMyOrderCourse(user.getId());
        return myOrderCourseList;
    }

    @Transactional
    @Override
    public void orderCourse(Integer courseId) {
        //添加课程关系表
        courseOrderService.add(courseId);
        //更新课程订阅数量
        courseService.updateOrderNums(courseId);

    }

    @Transactional
    @Override
    public void cancelCource(Integer courseId) {
        //更新课程关系表
        courseOrderService.delete(courseId);
        //更新课程订阅数量
        courseService.deductOrderNums(courseId);
    }

}
