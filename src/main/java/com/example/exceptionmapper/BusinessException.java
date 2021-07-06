package com.example.exceptionmapper;

public class BusinessException extends RuntimeException {

    private ErrorEnum errorEnum;

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
    }



    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
