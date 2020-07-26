package com.moon.demo.golf23.o_observer;

/**
 * @Description:步骤一：主题Subject
 * @Author：xiaojiaxin
 * @Date：2020-04-07 10:06
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 首先定义一个观察者数组，并实现增、删及通知操作。
 * 它的职责很简单，就是定义谁能观察，谁不能观察，用Vector是线程同步的，比较安全，
 * 也可以使用ArrayList，是线程异步的，但不安全。
 */
public class A_Subject {
    //观察者数组
    //private Vector<Observer>  vector = new Vector<>();
    private List<B_Observer> list = new ArrayList<>();

    //增加一个观察者
    public void addObserver(B_Observer BObserver) {
        this.list.add(BObserver);
    }

    //删除一个观察者
    public void delObserver(B_Observer BObserver) {
        this.delObserver(BObserver);
    }

    //通知所有观察者
    public void notifyObserver() {
        for (B_Observer BObserver : list) {
            BObserver.update();
        }
    }

}
