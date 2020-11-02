package com.config.server.config;

import com.config.server.Interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Autowired
    ResponseResultInterceptor responseResultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口验签拦截
        registry.addInterceptor(responseResultInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("")
                .excludePathPatterns("*.jpg", "*.png", "*.html", "*.jpeg")
                ;
    }

}
