package com.scb.test.modular.student.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Description 学生订课实体
 * @Author zhangheng
 */

@Data
@Entity
public class CourseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键

    private Integer userId; //学生账号id

    private Integer courseId; //课程id

    private String status; //是否订阅（0-未订阅  1-订阅）
}
