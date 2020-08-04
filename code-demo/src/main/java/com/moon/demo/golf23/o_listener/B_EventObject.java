package com.moon.demo.golf23.o_listener;

/**
 * 描述:事件对象
 * 创建人: 小月
 * 创建时间: 2020-07-27 23:43
 */
public class B_EventObject extends java.util.EventObject {

    public B_EventObject(Object source) {
        super(source);
    }

    public void doEvent() {
        System.out.println("通知一个事件源 source:" + this.getSource());
    }


}
