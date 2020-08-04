package com.moon.demo.golf23.o_listener;

/**
 * 描述:测试执行
 * 创建人: 小月
 * 创建时间: 2020-07-27 23:45
 */
public class D_EventDemo {
    public static void main(String[] args) {
        C_EventSource eventSource = new C_EventSource(); // 事件源

        // 事件源 调用监听器的一个方法，并传递事件对象
        eventSource.addListener(eventObject -> {
            eventObject.doEvent();
            if (eventObject.getSource().equals("closeWindow")) {
                System.out.println("doClose"); // 回调
            }
        });

        B_EventObject eventObject = new B_EventObject("closeWindow"); // 事件对象

        eventSource.notifyListenerEvent(eventObject); // 触发事件
    }
}
