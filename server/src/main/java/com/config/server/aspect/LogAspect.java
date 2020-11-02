package com.config.server.aspect;

import com.alibaba.fastjson.JSON;
import com.config.server.pojo.ReqLog;
import com.config.server.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/23 16:26
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("execution(* com.config.server.controller.*.*(..)))")
    public void saveLog(){}

    @Before("saveLog()")
    public void saveLog(JoinPoint joinPoint) throws InterruptedException {
        // 获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;

        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取请求参数
        String[] argNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        log.info("请求路径:{},请求方式:{},请求参数:{},IP:{}",request.getRequestURI(),
                request.getMethod(),
                getRequestParam(argNames,args),
                request.getRemoteAddr());
        // 日志入库
        ReqLog requestLog = new ReqLog();
        requestLog.setRequestUrl(request.getRequestURI());
        requestLog.setRequestType(request.getMethod());
        requestLog.setRequestParam(request.getRequestURI());
        requestLog.setIp(request.getRemoteAddr());
        logService.saveLog(requestLog);
    }

    /**
     * 组装请求参数
     * @param argNames 参数名称
     * @param args 参数值
     * @return 返回JSON串
     */
    private String getRequestParam(String[] argNames, Object[] args){
        HashMap<String,Object> params = new HashMap<>(argNames.length);
        if(argNames.length > 0 && args.length > 0){
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i] , args[i]);
            }
        }
        return JSON.toJSONString(params);
    }
}
