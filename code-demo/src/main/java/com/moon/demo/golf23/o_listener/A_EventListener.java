package com.moon.demo.golf23.o_listener;

/**
 * 描述:监听器接口
 * EventListener是一个回调接口类
 * handleEvent是一个回调函数接口
 * 通过回调模型
 * EventSource事件源便可回调具体监听器动作
 * 创建人: 小月
 * 创建时间: 2020-07-27 23:42
 */
public interface A_EventListener extends java.util.EventListener {

    /**
     * 事件处理
     */
    void handleEvent(B_EventObject eventObject);


}
