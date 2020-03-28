package com.moon.im.chat;

import com.moon.im.chat.core.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 步骤二:netty启动器类
 * 容器加载完毕后启动netty
 */

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    /**
     *
     */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			try {
				WSServer.getInstance().start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
