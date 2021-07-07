package com.example.exceptionmapper;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;

/**
 * 全局异常处理
 */
@Provider
@Slf4j
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    @ConfigProperty(name = "quarkus.profile")
    String profile;

    @Override
    public Response toResponse(Throwable exception) {

        log.error(exception.getMessage(), exception);
        String message = exception.getMessage();

        //根据环境决定是否隐藏异常详情
        if ("prod".equals(profile)) {
            message = "未知错误";
        }
        return Response.serverError().entity(message).build();
    }

    /**
     * 自定义异常处理
     */
    @Provider
    public static class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
        @Override
        public Response toResponse(BusinessException exception) {
            log.error(exception.getMessage(), exception);
            HashMap<String, String> map = new HashMap<>(2);
            map.put("code", exception.getErrorEnum().getCode());
            map.put("message", exception.getMessage());
            return Response.serverError().entity(map).build();
        }
    }
}
