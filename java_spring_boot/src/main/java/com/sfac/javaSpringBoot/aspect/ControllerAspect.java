package com.sfac.javaSpringBoot.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {

    private final static Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.sfac.javaSpringBoot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){}

    //前置通知
    @Before(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinpoint){
        LOGGER.debug("*************** before controller *************");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        LOGGER.debug("请求来源=="+request.getRemoteAddr());
        LOGGER.debug("请求URL=="+request.getRequestURL());
        LOGGER.debug("请求方式=="+request.getMethod());
        LOGGER.debug("响应的方式=="+joinpoint.getSignature().getDeclaringTypeName()+"."
       +joinpoint.getSignature().getName());
        LOGGER.debug("请求参数=="+ Arrays.toString(joinpoint.getArgs()));
    }

    //环绕通知
    @Around(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object arroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("************** arround controller ****************");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    //后置通知
    @After(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void  afterController(){
        LOGGER.debug("****************** after controller *************");
    }
}