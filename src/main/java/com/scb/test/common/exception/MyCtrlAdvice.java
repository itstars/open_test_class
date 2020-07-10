package com.scb.test.common.exception;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 异常处理
 * @Author zhangheng
 */

@Order(0)
@ControllerAdvice(annotations = Controller.class)
public class MyCtrlAdvice {

    @ExceptionHandler
    @ResponseBody
    public String ExceptionHandler(BusinessException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseBody
    public String ExceptionHandler(Exception e) {
        return "页面出错啦....";
    }
}
