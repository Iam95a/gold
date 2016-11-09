package com.cn.chen.log.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ch on 2016/10/28.
 */
@Aspect
@Component
public class LogAop {
    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.cn.chen.log.Log)")
    public void doAll(){

    }
    @Before("doAll()")
    public void beforeExec(JoinPoint joinpoint){
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String remoteAddr=request.getRemoteAddr();
        System.out.println("remoteAddr:"+remoteAddr);
    }

    @AfterReturning(pointcut = "doAll()",returning = "returnValue")
    public void afterExec(JoinPoint joinPoint,Object returnValue){
        System.out.println(returnValue.toString());

    }

}
