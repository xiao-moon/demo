package com.moon.im.chat.core;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * @Description: 用户id和channel的关联关系处理
 */
public class UserChannelRel {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String senderId, Channel channel) {
        manager.put(senderId, channel);
    }

    public static Channel get(String senderId) {
        return manager.get(senderId);
    }

    public static void remove(String senderId, Channel channel) {
        if (senderId != null) {
            manager.remove(senderId);
            return;
        } else if(channel != null){
            for (String key : manager.keySet()) {
                if (channel == manager.get(key)) {
                    manager.remove(key);
                }
            }
        }
    }

    public static void output() {
        for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
            System.out.println("UserId: " + entry.getKey()
                    + ", ChannelId: " + entry.getValue().id().asLongText());
        }
    }
}
