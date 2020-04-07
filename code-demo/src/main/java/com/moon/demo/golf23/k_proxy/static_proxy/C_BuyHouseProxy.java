package com.moon.demo.golf23.k_proxy.static_proxy;

/**
 * @Description:第三步：创建代理类
 * @Author：xiaojiaxin
 * @Date：2020-04-07 14:16
 */
public class C_BuyHouseProxy implements A_BuyHouse{

    private A_BuyHouse buyHouse;

    public C_BuyHouseProxy(final A_BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前准备...交给中介");
        buyHouse.buyHouse();
        System.out.println("买房后装修...交给装修工");
    }
}
