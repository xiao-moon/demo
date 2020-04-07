package com.moon.demo.golf23.k_proxy.static_proxy;

/**
 * @Description:第四步：编写测试类
 * @Author：xiaojiaxin
 * @Date：2020-04-07 14:18
 */
public class D_StaticProxyClient {
    public static void main(String[] args) {
        B_BuyHouseImpl b_buyHouse = new B_BuyHouseImpl();
        C_BuyHouseProxy c_buyHouseProxy = new C_BuyHouseProxy(b_buyHouse);
        c_buyHouseProxy.buyHouse();
    }


}
