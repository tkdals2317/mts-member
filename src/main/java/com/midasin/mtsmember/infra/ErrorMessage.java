package com.midasin.mtsmember.infra;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "패스워드가 올바르지 않습니다."),
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),

    INVALID_PARAM(HttpStatus.BAD_REQUEST, "적합하지 않은 파라미터")
    ;

    private final HttpStatus httpStatus;
    private final String message;


    ErrorMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}
