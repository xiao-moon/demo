package com.moon.demo.spring.c_interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:主拦截器
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:49
 */
//主拦截器，根据拦截不同路径跳转不同自定义拦截器 （实现WebMvcConfigurer方法）
@Configuration
public class MainInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new SubInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(getInterceptor()).addPathPatterns("/api/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public static HandlerInterceptor getInterceptor(){
        return new SubInterceptor();
    }


}
