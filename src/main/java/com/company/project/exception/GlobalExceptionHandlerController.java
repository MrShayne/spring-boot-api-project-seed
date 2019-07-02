package com.company.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * 全局异常控制
 */
@RestControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
//        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
        System.out.println("Access denied");
        res.getWriter().write("Above your paycheck");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res, Exception ex) throws IOException {
        System.out.println("exception");
        res.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


}
