package com.moon.demo.rabbitmq.producer;

import com.moon.demo.rabbitmq.config.DelayConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:延时消息生产者
 * @Author：xiaojiaxin
 * @Date：2020-02-18 12:30
 */
@Component
public class DelayProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, Integer type) {

        switch (type) {
            case 10:
                rabbitTemplate.convertAndSend(DelayConfig.DELAY_EXCHANGE_NAME, DelayConfig.DELAY_QUEUEA_ROUTING_KEY, msg,message -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    // 方式二:设置这条消息的过期时间
                    messageProperties.setExpiration("5000");
                    return message;
                });
                break;
            case 60:
                rabbitTemplate.convertAndSend(DelayConfig.DELAY_EXCHANGE_NAME, DelayConfig.DELAY_QUEUEB_ROUTING_KEY, msg,message -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    // 方式二:设置这条消息的过期时间
                    messageProperties.setExpiration("6000");
                    return message;
                });
                break;
        }
    }


}
