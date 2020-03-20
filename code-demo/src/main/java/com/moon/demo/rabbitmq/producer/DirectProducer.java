package com.moon.demo.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:消息生产者
 * @Author：xiaojiaxin
 * @Date：2020-02-17 19:31
 */
@Component
public class DirectProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send1(String msg) throws Exception {
//        Map map = new HashMap();
//        map.put("age",18);
//        map.put("name","moon");
//        String s = objectMapper.writeValueAsString(map);

        // 生产者发送消息的时候需要设置消息id
//        Message message = MessageBuilder.withBody(s.getBytes())
//                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
//                        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                        .setContentEncoding("UTF-8")
//                        .build();
        logger.info("notify.payment send message: "+msg);
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }




}
