package com.moon.demo.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:TopicConsumer
 * @Author：xiaojiaxin
 * @Date：2020-02-18 2:24
 */
@Component
public class TopicConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 监听routingKey为api.core的队列消息
     */
    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void user(String msg) {
        logger.info("api.core receive message: "+msg);
    }

    /**
     * 监听routingKey为api.payment的队列消息
     */
    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void order(String msg) {
        logger.info("api.payment.order receive message: "+msg);
    }



}
