package com.simplemessage.cloudsimple.Infrastructure.exception;

/**
 * 异常枚举信息
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/03/15 21:46:45
 * @ModifyAt: 2023/03/15 21:46:45
 * @Version 1.0
 */
public interface IExceptionEnum {

    /**
     * 获取Code
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取message
     *
     * @return
     */
    String getMessage();
}
