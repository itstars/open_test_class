package com.scb.test.modular.student.repo;

import com.scb.test.modular.student.entity.CourseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 课程订阅服务
 * @Author zhangheng
 */
public interface CourseOrderRepo extends JpaRepository<CourseOrder,Integer> {
}
