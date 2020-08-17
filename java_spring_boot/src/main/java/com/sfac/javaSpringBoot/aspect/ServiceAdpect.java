package com.sfac.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ServiceAdpect {

    private final static Logger LOGGER=LoggerFactory.getLogger(ServiceAdpect.class);

    @Pointcut("@annotation(com.sfac.javaSpringBoot.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){}

    //前置通知
    @Before(value = "com.sfac.javaSpringBoot.aspect.ServiceAdpect.servicePointCut()")
    public void beforeService(JoinPoint joinpoint){
        LOGGER.debug("*************** before Service *************");
      /*  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        LOGGER.debug("请求来源=="+request.getRemoteAddr());
        LOGGER.debug("请求URL=="+request.getRequestURL());
        LOGGER.debug("请求方式=="+request.getMethod());
        LOGGER.debug("响应的方式=="+joinpoint.getSignature().getDeclaringTypeName()+"."
                +joinpoint.getSignature().getName());
        LOGGER.debug("请求参数=="+ Arrays.toString(joinpoint.getArgs()));*/
    }

    //环绕通知
    @Around(value = "com.sfac.javaSpringBoot.aspect.ServiceAdpect.servicePointCut()")
    public Object arroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("************** arround Service ****************");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    //后置通知
    @After(value = "com.sfac.javaSpringBoot.aspect.ServiceAdpect.servicePointCut()")
    public void  afterService(){

        LOGGER.debug("****************** after Service *************");
    }
}
