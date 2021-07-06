package com.example.exceptionmapper;

public enum ErrorEnum {

    UNKNOWN("未定义的错误", "500");
    private String message;
    private String code;

    ErrorEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
