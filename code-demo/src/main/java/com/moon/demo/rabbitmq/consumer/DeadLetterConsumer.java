package com.moon.demo.rabbitmq.consumer;

import com.moon.demo.rabbitmq.config.DeadLetterConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-18 11:38
 */
@Component
public class DeadLetterConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * 业务队列的消费代码
     */

    @RabbitListener(queues = DeadLetterConfig.BUSINESS_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        logger.info("收到业务消息A：{}", msg);
        boolean ack = true;
        Exception exception = null;
        try {
            if (msg.contains("deadletter")){
                throw new RuntimeException("dead letter exception");
            }
        } catch (Exception e){
            ack = false;
            exception = e;
        }
        if (!ack){
            logger.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = DeadLetterConfig.BUSINESS_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到业务消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    /**
     * 配置死信队列的消费者
     */
    @RabbitListener(queues = DeadLetterConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveDaeadA(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息A：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DeadLetterConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveDaeadB(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }









}
