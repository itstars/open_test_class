package com.scb.test.modular.student.service;

import com.scb.test.modular.teacher.entity.Course;

import java.util.List;

/**
 * @Description 订课服务接口
 * @Author zhangheng
 */
public interface CourseOrderService {

    void add(Integer courseId);

    void delete(Integer courseId);

    List<Course> findMyOrderCourse(Integer userId);
}
