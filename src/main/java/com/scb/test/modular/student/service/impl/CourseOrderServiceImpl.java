package com.scb.test.modular.student.service.impl;

import com.scb.test.common.Contants;
import com.scb.test.common.exception.BusinessException;
import com.scb.test.modular.student.entity.CourseOrder;
import com.scb.test.modular.student.repo.CourseOrderRepo;
import com.scb.test.modular.student.service.CourseOrderService;
import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.teacher.entity.Course;
import com.scb.test.modular.teacher.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 课程订购服务实现
 * @Author zhangheng
 */

@Service
public class CourseOrderServiceImpl implements CourseOrderService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseOrderRepo courseOrderRepo;

    @Override
    public List<Course> findMyOrderCourse(Integer userId) {
        List<Course> courseList = new ArrayList<>();
        //查询已订阅课程
        CourseOrder myOrder = new CourseOrder();
        myOrder.setUserId(userId);
        myOrder.setStatus(Contants.STATUE_ON);
        Example<CourseOrder> example = Example.of(myOrder);
        List<CourseOrder> myOrderCourseList = courseOrderRepo.findAll(example);
        myOrderCourseList.stream().forEach(orderCourse->{
            Integer courseId = orderCourse.getCourseId();
            Course orderedCourse = courseService.findById(courseId);
            courseList.add(orderedCourse);
        });
        return courseList;
    }

    @Override
    public void add(Integer courseId) {
        //查询该课程是否已订阅
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CourseOrder orderOld = new CourseOrder();
        orderOld.setUserId(user.getId());
        orderOld.setCourseId(courseId);
        orderOld.setStatus(Contants.STATUE_ON);
        Example<CourseOrder> example = Example.of(orderOld);
        List<CourseOrder> oldCourses = courseOrderRepo.findAll(example);
        if(!CollectionUtils.isEmpty(oldCourses)){
            throw new BusinessException("400","已订阅该课程，无法重复订阅!");
        }
        CourseOrder order = new CourseOrder();
        order.setUserId(user.getId());
        order.setCourseId(courseId);
        order.setStatus(Contants.STATUE_ON);
        courseOrderRepo.save(order);
    }

    @Override
    public void delete(Integer courseId) {
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CourseOrder orderOld = new CourseOrder();
        orderOld.setUserId(user.getId());
        orderOld.setCourseId(courseId);
        Example<CourseOrder> example = Example.of(orderOld);
        List<CourseOrder> oldCourses = courseOrderRepo.findAll(example);
        oldCourses.stream().forEach(order->{
            order.setStatus(Contants.STATUE_OFF);
            courseOrderRepo.save(order);
        });
    }
}
