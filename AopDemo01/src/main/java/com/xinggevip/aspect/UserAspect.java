package com.xinggevip.aspect;

/**
 * @author xinggevip
 * @date 2020/7/17 14:17
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserAspect {

    // 给全路径起别名
    @Pointcut(value = "execution(* com.xinggevip.pojo.User.eat(..))")
    private void pointcute1(){}

    // 环绕通知try catch写法，包含所有通知
    @Around(value = "UserAspect.pointcute1()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        try {
            // 获取请求参数
            Object[] args = joinPoint.getArgs();
            Object arg = args[0];
            System.out.println("获取第一个请求参数：" + arg);
            // 前置通知
            System.out.println("User------前置通知");
            // 执行方法体，此方法体可以执行任意次
            proceed = joinPoint.proceed();
            // 后置通知
            System.out.println("user----后置通知");
        }catch (Throwable throwable){
            // 异常通知
            throwable.printStackTrace();

        }finally {
            // 最终通知
            System.out.println("user------最终通知");
            return proceed;
        }
    }


    @Before(value = "execution(* com.xinggevip.pojo.User.eat(..))")
    public void log(JoinPoint joinPoint){
        System.out.println("=========BeforeStart=========");
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取方法名
//        Signature signature = joinPoint.getSignature(); // 会获取到全路径的方法名 String com.xinggevip.pojo.User.eat(String)
        String methodName = joinPoint.getSignature().getName(); // 仅仅获取到方法名 eat
        System.out.println("前置通知====" + "方法名字为：" + methodName + "方法参数为：" + Arrays.toString(args));
        System.out.println("=========BeforeEnd=========");
    }


}
