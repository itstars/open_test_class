package com.scb.test.modular.teacher.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Description 课程实体类
 * @Author zhangheng
 */
@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键

    private String name; //课程名称

    private String content; //课程描述

    private Integer ordernums; //课程订阅数量

    private String status; //课程状态（0-无效  1-有效）
}
