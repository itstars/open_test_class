package com.scb.test.modular.teacher.service;

import com.scb.test.modular.system.entity.SysUser;
import com.scb.test.modular.teacher.entity.Course;
import java.util.List;

/**
 * @Description 课程服务类
 * @Author zhangheng
 */
public interface CourseService {

    List<Course> findAll();

    List<Course> findCourse();

    Course findById(Integer id);

    Course addCourse(Course course);

    Course updateCourse(Course course);
    void deleteCource(Integer id);

    void updateOrderNums(Integer courseId);

    void deductOrderNums(Integer courseId);
}
