package com.moon.demo.golf23.o_observer;

/**
 * @Description:步骤三：具体主题
 * @Author：xiaojiaxin
 * @Date：2020-04-07 10:15
 */
public class C_ConcreteASubject extends A_Subject {
    //具体业务
    public void doSomething(){
        System.out.println("我今天干活了");
        super.notifyObserver();
    }
}
