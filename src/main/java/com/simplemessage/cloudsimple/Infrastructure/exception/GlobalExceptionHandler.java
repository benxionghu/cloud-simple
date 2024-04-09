package com.simplemessage.cloudsimple.Infrastructure.exception;

import com.simplemessage.cloudsimple.Infrastructure.result.Result;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/03/15 21:48:31
 * @ModifyAt: 2023/03/15 21:48:31
 * @Version 1.0
 */
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String UNIQUE_ID = "traceId";

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',运行时发生未知异常.", requestUri, e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常,message={}", requestUri, e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e, HttpServletRequest request) {
        log.error("自定义验证异常:{}", e.getMessage());
        String message = e.getMessage();
        return Result.fail(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("自定义异常{}", e.getMessage());
        String message = e.getMessage();
        return Result.fail(message);
    }

    /**
     * 自定义Api异常信
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public Object handleMethodApiException(ApiException e, HttpServletRequest request) {
        log.error("ApiException:{}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }
}
