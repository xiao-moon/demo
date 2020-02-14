package com.moon.demo.spring.b_listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:监听器
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:45
 */
/*一般是获取在线人数等业务需求*/
@WebListener
public class MyListener implements ServletRequestListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.error("监听器被销毁...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.error("监听器初始化开始工作...");
    }
}
