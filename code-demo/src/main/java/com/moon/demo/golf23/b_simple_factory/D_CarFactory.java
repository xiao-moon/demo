package com.moon.demo.golf23.b_simple_factory;

/**
 * 描述:汽车工厂
 * 创建人: 小月
 * 创建时间: 2020-07-28 00:45
 */
public class D_CarFactory {

    public A_Car createCar(String carType) {
        if (carType.equalsIgnoreCase("马自达")) return new B_Mazda();
        if (carType.equalsIgnoreCase("奔驰")) return new C_Benz();
        return null;
    }

}
