package com.simplemessage.cloudsimple.Infrastructure.exception;

import lombok.Data;

/**
 * 统一返回异常信息
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/03/15 21:44:25
 * @ModifyAt: 2023/03/15 21:44:25
 * @Version 1.0
 */
@Data
public class ApiException extends RuntimeException {
    private Integer code;

    /**
     * 业务异常信息
     *
     * @param code    业务代码
     * @param message 业务异常信息
     */
    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 业务异常信息
     *
     * @param exception
     */
    public ApiException(IExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
    }
}
