package com.itheima.aop;

import com.itheima.anno.LogOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
//@Order(4)
@Component
//@Aspect
public class MyAspect5 {
    //前置通知
    //@Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect4 -> before ...");
    }

    //后置通知
    //@After("execution(* com.itheima.service.impl..*(..))")
    public void after(){
        log.info("MyAspect4 -> after ...");
    }

    //@Before("execution(* com.itheima.service.impl.*.*(..))")
    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before1(){
        log.info("MyAspect4 -> before ...");
    }
}
