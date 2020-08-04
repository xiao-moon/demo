package com.moon.demo.golf23.o_listener;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:事件源
 * 创建人: 小月
 * 创建时间: 2020-07-27 23:43
 */
public class C_EventSource {
    // 监听器列表，监听器的注册 加入此列表
    private List<A_EventListener> listeners = new ArrayList<>();

    public void addListener(A_EventListener eventListener) {
        listeners.add(eventListener);
    }

    public void removeListener(A_EventListener eventListener) {
        listeners.remove(eventListener);
    }

    public void notifyListenerEvent(B_EventObject eventObject) {
        for (A_EventListener eventListener : listeners) {
            eventListener.handleEvent(eventObject);
        }
    }

}
