package com.moon.demo.golf23.k_proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:第一步：编写动态处理器
 * @Author：xiaojiaxin
 * @Date：2020-04-07 14:27
 */
public class A_DynamicProxyHandler implements InvocationHandler {

    private Object object;

    public A_DynamicProxyHandler(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备...交给中介");
        Object result = method.invoke(object, args);
        System.out.println("买房后装修...交给装修工");
        return result;
    }
}
