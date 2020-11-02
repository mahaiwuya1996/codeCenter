/**
 * Copyright (C) 2016-2019, www.koudailingqian.com. All rights reserved.
 */
package com.config.server.exception;

import com.config.server.base.Result;
import com.config.server.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

/**
 * @description:全局异常处理
 * @author: Liwei
 * @date: 2020/9/21 14:02
 */
@Slf4j
@RestControllerAdvice
//Spring IOC容器中Bean的执行顺序的优先级 默认是最低优先级,值越小优先级越高
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {


    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public Result processException(CustomException e) {
        Result result = new Result();
        log.error("位置:{} -> 错误信息:{}", e.getMethod(), e.getLocalizedMessage());
        ResultEnum resultEnum = ResultEnum.getByCode(e.getCode());
        if (resultEnum != null) {
            return result.failure(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
        }
        return result.failure(e.getCode(), e.getLocalizedMessage());
    }

    /**
     * 拦截表单参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public Result bindException(BindException e) {
        Result result = new Result();
        BindingResult bindingResult = e.getBindingResult();
        return result.failure(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 拦截JSON参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result bindException(MethodArgumentNotValidException e) {
        Result result = new Result();
        BindingResult bindingResult = e.getBindingResult();
        return result.failure(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 参数类型不匹配格式错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Result result = new Result();
        log.error("错误信息{}", e.getLocalizedMessage());
        return result.failure(ResultEnum.ARGUMENT_TYPE_MISMATCH);
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadable(HttpMessageNotReadableException e) {
        Result result = new Result();
        log.error("错误信息:{}", e.getLocalizedMessage());
        return result.failure(ResultEnum.PARAMS_ERROR);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        Result result = new Result();
        log.error("错误信息:{}", e.getLocalizedMessage());
        return result.failure(ResultEnum.REQ_METHOD_NOT_SUPPORT);
    }

    /**
     * 通用异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        Result result = new Result();
        e.printStackTrace();
        return result.failure(ResultEnum.UNKNOWN_ERROR);
    }


}
