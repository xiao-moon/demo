package com.moon.demo.golf23.k_proxy.static_proxy;

/**
 * @Description:第二步：实现服务接口
 * @Author：xiaojiaxin
 * @Date：2020-04-07 14:15
 */
public class B_BuyHouseImpl implements A_BuyHouse {
    @Override
    public void buyHouse() {
        System.out.println("我要买房...自己掏钱");
    }
}
