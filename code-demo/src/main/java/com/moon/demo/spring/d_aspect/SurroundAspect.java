package com.moon.demo.spring.d_aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:环绕通知
 * @Author：xiaojiaxin
 * @Date：2020-02-11 16:04
 */
@Aspect//需要添加aop的依赖
@Component
public class SurroundAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *切入点(注解)
     * 1、在那些方法上起作用
     * 2、在什么时候起作用
     * 增强(方法)
     * 起作用时执行的业务逻辑
     */
    @Around("execution(* com.moon.demo.spring.d_aspect.TestController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        logger.error("aspect开始");
        /*获取方法参数的值*/
        Object[] args = pjp.getArgs();
        /*方法执行的返回值*/
        Object object = pjp.proceed();
        logger.error("aspect结束");
        return object;
    }



}
