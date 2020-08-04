package com.moon.demo.golf23.b_simple_factory;

/**
 * 描述:
 * 创建人: 小月
 * 创建时间: 2020-07-28 00:47
 */
public class E_Demo {
    public static void main(String[] args) {
        D_CarFactory carFactory = new D_CarFactory();
        A_Car car1 = carFactory.createCar("马自达");
        A_Car car2 = carFactory.createCar("奔驰");
        car1.run();
        car2.run();
    }
}
