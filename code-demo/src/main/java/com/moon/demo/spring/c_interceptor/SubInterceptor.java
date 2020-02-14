package com.moon.demo.spring.c_interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:子拦截器
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:50
 */
public class SubInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入controller方法之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.error("进入controller方法之前...");
        return true;
    }

    /**
     * 调用完controller之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.error("调用完controller之后，视图渲染之前...");

    }

    /**
     * 整个完成之后，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.error("整个完成之后，通常用于资源清理...");
    }
}
