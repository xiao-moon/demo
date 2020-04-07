package com.moon.demo.golf23.k_proxy.dynamic_proxy;

import com.moon.demo.golf23.k_proxy.static_proxy.A_BuyHouse;
import com.moon.demo.golf23.k_proxy.static_proxy.B_BuyHouseImpl;

import java.lang.reflect.Proxy;

/**
 * @Description:第二步：编写测试类
 * @Author：xiaojiaxin
 * @Date：2020-04-07 14:30
 */
public class B_DynamicProxyClient {
    public static void main(String[] args) {
        A_BuyHouse b_buyHouse = new B_BuyHouseImpl();

        // Proxy.newProxyInstance()方法接受三个参数:
        // ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
        // Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
        // InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法
        A_BuyHouse proxyBuyHouse = (A_BuyHouse) Proxy.newProxyInstance(A_BuyHouse.class.getClassLoader(),
                new Class[]{A_BuyHouse.class},
                new A_DynamicProxyHandler(b_buyHouse));
        proxyBuyHouse.buyHouse();

    }

}
