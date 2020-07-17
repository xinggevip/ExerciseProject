package com.xinggevip.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1) // 多个切面，指定执行顺序，数字越小，执行越靠前
public class TestAspect {

    @Before(value = "execution(* com.xinggevip.pojo.User.eat(..))")
    public void before(){
        System.out.println("TestAspect=>前置通知");
    }
}