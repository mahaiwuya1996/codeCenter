package com.config.server.annotation;

import java.lang.annotation.*;

/**
 * @description: 结果集注解
 * @author: LiWei
 * @date: 2020/11/2 13:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
