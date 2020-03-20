package com.moon.demo.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:TopicProducer
 * @Author：xiaojiaxin
 * @Date：2020-02-18 2:08
 */
@Component
public class TopicProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 添加一个user()方法，发送消息至coreExchange交换机且routingKey为api.core.user
     */
    public void user(String msg){
        logger.info("api.core.user send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    /**
     * 添加一个userQuery()方法，发送消息至coreExchange交换机且routingKey为api.core.user.query
     */
    public void userQuery(String msg){
        logger.info("api.core.user.query send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
    }

    /**
     * 添加一个order()方法，发送消息至paymentExchange交换机且routingKey为api.payment.order
     */
    public void order(String msg){
        logger.info("api.payment.order send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    /**
     * 添加一个orderQuery()方法，发送消息至paymentExchange交换机且routingKey为api.payment.order.query
     */
    public void orderQuery(String msg){
        logger.info("api.payment.order.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    /**
     * 添加一个orderDetailQuery()方法，发送消息至paymentExchange交换机且routingKey为api.payment.order.detail.query
     */
    public void orderDetailQuery(String msg){
        logger.info("api.payment.order.detail.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }




}
