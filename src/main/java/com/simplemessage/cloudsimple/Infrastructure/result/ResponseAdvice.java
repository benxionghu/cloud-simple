package com.simplemessage.cloudsimple.Infrastructure.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回
 *
 * @Author: benxiong.hu
 * @CreateAt: 2022/11/28 14:50
 * @ModifyAt: 2022/11/28 14:50
 * @Version 1.0
 */
@RestControllerAdvice(basePackages = "com.simplemessage.*")
@AllArgsConstructor
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    private ObjectMapper objectMapper;

    /**
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        if (body instanceof String) {
            try {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return this.objectMapper.writeValueAsString(Result.success(body));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(body);
    }
}
