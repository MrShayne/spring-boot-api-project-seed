package com.company.project.exception;

import org.springframework.http.HttpStatus;

/**
 * @Date: 2019-05-30 11:58
 */
public class CustomException extends RuntimeException{

    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
