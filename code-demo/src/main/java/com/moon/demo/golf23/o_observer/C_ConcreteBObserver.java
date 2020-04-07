package com.moon.demo.golf23.o_observer;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-04-07 11:00
 */
public class C_ConcreteBObserver implements B_Observer {
    @Override
    public void update() {
        System.out.println("进行update更新");
    }
}
