package com.simplemessage.cloudsimple.Infrastructure.result;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 接口统一响应对象
 *
 * @author benxiong.hu
 */
public class Result<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 返回成功结果
     */
    private final static String SUCCESS = "操作成功";
    /**
     * 返回失败结果
     */
    private final static String FAIL = "操作失败";
    /**
     * 返回状态码
     */
    private int code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据对象
     */
    private T data;
    private Long timestamp;

    /**
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.SUCCESS);
        responseResult.setMsg(SUCCESS);
        responseResult.setTimestamp(System.currentTimeMillis());
        return responseResult;
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.SUCCESS);
        responseResult.setMsg(SUCCESS);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(int code, T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(code);
        responseResult.setMsg(SUCCESS);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg, T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.SUCCESS);
        responseResult.setMsg(msg);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(int code, String msg, T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.SUCCESS);
        responseResult.setMsg(FAIL);
        responseResult.setTimestamp(System.currentTimeMillis());
        return responseResult;
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.FAIL);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setMsg(FAIL);
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * 返回错误消息
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String message) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.FAIL);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setMsg(message);
        return responseResult;
    }

    /**
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(int code, T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(code);
        responseResult.setMsg(FAIL);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String msg, T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(ResultCode.FAIL);
        responseResult.setMsg(msg);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        Result<T> responseResult = new Result<T>();
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        responseResult.setTimestamp(System.currentTimeMillis());
        responseResult.setData(data);

        return responseResult;
    }

    /**
     * 返回成功分页信息
     *
     * @param page
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<PageResult<T>> pageSuccess(Page<T> page, IPage<T> data) {
        return Result.success(PageResult.<T>builder()
                        .totalCount(data.getTotal())
                        .currentIndex(page.getSize())
                        .currentPage(page.getCurrent())
                        .result(data.getRecords())
                        .build())
                .setCode(ResultCode.SUCCESS)
                .setMsg(SUCCESS)
                .setTimestamp(System.currentTimeMillis());
    }

    /**
     * 返回成功分页信息
     *
     * @param page
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<PageResult<T>> pageError(Page<T> page, IPage<T> data) {
        return Result.success(PageResult.<T>builder()
                        .totalCount(0L)
                        .currentIndex(0L)
                        .currentPage(0L).build())
                .setCode(ResultCode.FAIL)
                .setMsg(FAIL)
                .setTimestamp(System.currentTimeMillis());
    }


    /**
     * 获取状态码
     *
     * @return
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 设置状态码
     *
     * @param retCode
     * @return
     */
    public Result<T> setCode(ResultCode retCode) {
        this.code = retCode.code;
        return this;
    }

    /**
     * 设置状态码
     *
     * @param code
     * @return
     */
    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 获取返回结果
     *
     * @return
     */
    public Long getTimestamp() {
        return this.timestamp;
    }

    /**
     * 设置当前时间
     *
     * @param timestamp
     * @return
     */
    public Result<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 获取描述信息
     *
     * @return
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * 设置描述信息
     *
     * @param msg
     * @return
     */
    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 获取返回结果
     *
     * @return
     */
    public T getData() {
        return data;
    }


    /**
     * 设置返回消息
     *
     * @param data
     * @return
     */
    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

}
