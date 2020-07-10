package com.scb.test.modular.teacher.service.impl;

import com.scb.test.common.Contants;
import com.scb.test.common.exception.BusinessException;
import com.scb.test.modular.student.entity.CourseOrder;
import com.scb.test.modular.student.repo.CourseOrderRepo;
import com.scb.test.modular.teacher.entity.Course;
import com.scb.test.modular.teacher.repo.CourseRepo;
import com.scb.test.modular.teacher.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description 课程服务实现类
 * @Author zhangheng
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private CourseOrderRepo courseOrderRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> findCourse() {
        Course course = new Course();
        course.setStatus(Contants.STATUE_ON);
        Example<Course> example = Example.of(course);
        return courseRepo.findAll(example);
    }

    @Override
    public Course findById(Integer id) {
        return courseRepo.findById(id).get();
    }

    @Override
    public void updateOrderNums(Integer courseId) {
        //获取原订阅数量
        Course oldEntity = this.findById(courseId);
        Integer ordernums = oldEntity.getOrdernums();
        if(null == ordernums){
            ordernums=0;
        } else {
            ordernums++;
        }
        //更新订阅数量
        Course course = new Course();
        BeanUtils.copyProperties(oldEntity,course);
        course.setOrdernums(ordernums);
        this.courseRepo.save(course);
    }

    @Override
    public void deductOrderNums(Integer courseId) {
        //获取原订阅数量
        Course oldEntity = this.findById(courseId);
        Integer ordernums = oldEntity.getOrdernums();
        if(null == ordernums){
            ordernums=0;
        }else {
            ordernums--;
        }
        if(ordernums<0) ordernums=0;
        //更新订阅数量
        Course course = new Course();
        BeanUtils.copyProperties(oldEntity,course);
        course.setOrdernums(ordernums);
        this.courseRepo.save(course);
    }

    @Override
    public Course addCourse(Course course) {
        if(StringUtils.isEmpty(course.getName())){
            throw new BusinessException("400","课程名称能为空!");
        }
        course.setStatus(Contants.STATUE_ON);
        course.setOrdernums(0);
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        if(StringUtils.isEmpty(course.getName())){
            throw new BusinessException("400","课程名称能为空!");
        }
        //更新学生已订课程状态
        if(Contants.STATUE_OFF.equals(course.getStatus())){
            CourseOrder order = new CourseOrder();
            order.setCourseId(course.getId());
            Example<CourseOrder> example = Example.of(order);
            List<CourseOrder> courseOrders = courseOrderRepo.findAll(example);
            courseOrders.stream().forEach(olderOrder->{
                courseOrderRepo.delete(olderOrder);
            });
            course.setOrdernums(0); //无效课程取消订阅数量
        }
        return courseRepo.save(course);
    }

    @Override
    public void deleteCource(Integer id) {
        courseRepo.deleteById(id);
    }
}
