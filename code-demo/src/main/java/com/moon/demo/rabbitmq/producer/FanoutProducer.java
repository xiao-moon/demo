package com.moon.demo.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-18 2:35
 */
@Component
public class FanoutProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 添加一个generateReports()方法，发送消息至reportExchange交换机
     */
    public void generateReports(String msg){
        logger.info("api.generate.reports send message: "+msg);
        rabbitTemplate.convertAndSend("reportExchange", "api.generate.reports", msg);
    }


}
