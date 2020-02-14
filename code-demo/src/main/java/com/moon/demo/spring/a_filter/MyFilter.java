package com.moon.demo.spring.a_filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description:过滤器
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:38
 */
//过滤器拦截路径
@WebFilter(urlPatterns = "/api/*", filterName = "myFilter")
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 容器加载的时候调用
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.error("过滤器初始化...");
    }

    /**
     * 请求被拦截的时候进行调用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.error("过滤器被调用...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 容器被销毁的时候被调用
     */
    @Override
    public void destroy() {
        logger.error("过滤器被销毁...");
    }
}
