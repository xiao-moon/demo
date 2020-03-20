package com.moon.demo.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:FanoutConsumer
 * @Author：xiaojiaxin
 * @Date：2020-02-18 2:34
 */
@Component
public class FanoutConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 添加payment()方法，监听routingKey为api.report.payment的队列消息
     */
    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg) {
        logger.info("api.report.payment receive message: "+msg);
    }

    /**
     * 添加refund()方法，监听routingKey为api.report.refund的队列消息
     */
    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg) {
        logger.info("api.report.refund receive message: "+msg);
    }

}
