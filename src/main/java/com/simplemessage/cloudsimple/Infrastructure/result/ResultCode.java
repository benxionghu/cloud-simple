package com.simplemessage.cloudsimple.Infrastructure.result;

/**
 * 成功示例
 *
 * @Author: benxiong.hu
 * @CreateAt: 2022/11/28 14:50
 * @ModifyAt: 2022/11/28 14:50
 * @Version 1.0
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * 失败
     */
    FAIL(400),


    /**
     * 未认证
     */
    UNAUTHORIZED(401),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    /**
     * code码
     */
    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
