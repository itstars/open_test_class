package com.scb.test.modular.teacher.repo;

import com.scb.test.modular.teacher.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 课程Dao
 * @Author zhangheng
 */
public interface CourseRepo extends JpaRepository<Course,Integer> {
}
