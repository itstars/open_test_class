package com.scb.test.common.exception;

/**
 * @Description 业务异常
 * @Author zhangheng
 */
public class BusinessException extends RuntimeException{
    private String code;

    private String errorMessage;

    public BusinessException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
