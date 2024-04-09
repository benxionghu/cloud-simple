package com.simplemessage.cloudsimple.Infrastructure.exception;

/**
 * 异常枚举信息
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/03/15 21:47:16
 * @ModifyAt: 2023/03/15 21:47:16
 * @Version 1.0
 */
public enum ApiResultEnum implements IExceptionEnum {

    GET_USERINFO_ERROR(10001, "获取用户信息失败");

    /**
     * 错误码
     */
    private Integer errCode;

    /**
     * 异常说明
     */
    private String errMsg;

    /**
     * 提供带有两个参数的构造方法
     */
    ApiResultEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 根据枚举编码获取枚举对象
     *
     * @param errCode
     * @return
     */
    public static ApiResultEnum getByCode(Integer errCode) {
        for (ApiResultEnum current : ApiResultEnum.values()) {
            if (current.getErrCode().equals(errCode)) {
                return current;
            }
        }
        return null;
    }

    /**
     * 获取Code
     *
     * @return
     */
    @Override
    public Integer getCode() {
        return this.errCode;
    }

    /**
     * 获取message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return this.errMsg;
    }
}
