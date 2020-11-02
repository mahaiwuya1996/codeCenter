package com.config.server.handler;

import com.config.server.annotation.ResponseResult;
import com.config.server.base.Result;
import com.config.server.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/11/2 14:21
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    //注解标记
    public static final String RESPONSE_RESULT_ANN = "response_result_ann";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        ResponseResult responseResult = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResult == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("重写返回体数据--------------------------------------");
        if(o instanceof Result && ((Result) o).getCode() != ResultEnum.SUCCESS.getCode()){
            log.info("重写异常返回体数据---------------------------------");
            return o;
        }
        return Result.success(o);
    }
}
