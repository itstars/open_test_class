package com.scb.test.modular.student.service;

import com.scb.test.modular.teacher.entity.Course;

import java.util.List;

/**
 * @Description 学生服务接口
 * @Author zhangheng
 */
public interface StudentService {

    List<Course> findCourse();

    List<Course> findOrderCourse();

    void orderCourse(Integer courseId);

    void cancelCource(Integer id);
}
