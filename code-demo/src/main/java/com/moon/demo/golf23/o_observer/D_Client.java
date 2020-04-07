package com.moon.demo.golf23.o_observer;

/**
 * @Description:步骤四：Client客户端
 * @Author：xiaojiaxin
 * @Date：2020-04-07 10:58
 */

/**
 * 应用场景:
 * 聊天室程序的，服务器转发给所有客户端
 * 网络游戏多人联机对战场景中，服务器将客户端的状态进行分发
 * 邮件订阅
 * Servlet中，监听器的实现
 */

/**
 * 首先创建一个被观察者，然后定义一个观察者，将该被观察者添加到该观察者的观察者数组中，进行测试。
 */
public class D_Client {
    public static void main(String[] args) {
        //创建一个主题
        C_ConcreteASubject subject = new C_ConcreteASubject();
        //定义一个观察者
        B_Observer BObserver = new C_ConcreteBObserver();

        //观察
        subject.addObserver(BObserver);
        //开始活动
        subject.doSomething();

    }
}
