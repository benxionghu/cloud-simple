package com.simplemessage.cloudsimple.Infrastructure.config.log;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * 请求记录日志相关
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/09/18 22:44:45
 * @ModifyAt: 2023/09/18 22:44:45
 * @Version 1.0
 */
@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class ControllerAopInterceptor {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private static final String UNIQUE_ID = "traceId";

    @Pointcut(value = "execution(public * com.simplemessage.*.web.controller.*.*(..))")
    public void privilege() {
    }

    @Around("privilege()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取类名
        String className = pjp.getTarget().getClass().getName();
        // 获取执行的方法名称
        String methodName = pjp.getSignature().getName();
        // 获取参数名称
        String[] parameterNamesArgs = ((MethodSignature) pjp.getSignature()).getParameterNames();
        // 定义返回参数
        Object result = null;
        // 获取方法参数
        Object[] args = pjp.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 请求的URL
        String requestURL = request.getRequestURL().toString();
        String ip = getIpAddr(request);

        Enumeration<String> parameterNames = request.getParameterNames();
        String param = "";
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        if (!ObjectUtils.isEmpty(parameterNames)) {
            param = JSON.toJSONString(getRequestParamJSON(requestWrapper));
            log.info("request param   is:{}", param);
        }

        StringBuffer paramsBuf = new StringBuffer();
        // 获取请求参数集合并进行遍历拼接
        for (int i = 0; i < args.length; i++) {
            if (paramsBuf.length() > 0) {
                paramsBuf.append("|");
            }
            paramsBuf.append(parameterNamesArgs[i]).append(" = ").append(args[i]);
        }
        StringBuffer headerBuf = new StringBuffer();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            if (headerBuf.length() > 0) {
                headerBuf.append("|");
            }
            headerBuf.append(key).append("=").append(value);
        }

        String traceId = request.getHeader(UNIQUE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }

        long start = System.currentTimeMillis();
        log.info("http ip is:{} || traceId is {}", ip, traceId);
        log.info("http methodName is {}, requestUrl is {} class is {}", methodName, requestURL, className);
        log.info("http header is {}", headerBuf);
        log.info("http params is {}", paramsBuf.toString());
        log.info("http request time is {}", start);
        // 执行目标方法
        result = pjp.proceed();
        // 获取执行完的时间 打印返回报文
        log.info("http result is {}", JSONObject.toJSONString(result));
        log.info("http consumption of time is {}", (System.currentTimeMillis() - start));
        return result;

    }

    /**
     * 请求参数列表
     *
     * @param requestWrapper
     * @return
     */
    private JSONObject getRequestParamJSON(ContentCachingRequestWrapper requestWrapper) {
        Enumeration<String> parameterEnum = requestWrapper.getParameterNames();
        JSONObject paramJsonObject = new JSONObject();
        if (!ObjectUtils.isEmpty(parameterEnum)) {
            while (parameterEnum.hasMoreElements()) {
                String name = parameterEnum.nextElement();
                String[] value = requestWrapper.getParameterValues(name);
                paramJsonObject.put(name, value);

            }
        }
        return paramJsonObject;
    }

    /**
     * @Description: 获取ip
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        // 或者这样也行,对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        return ipAddress;
    }

}
