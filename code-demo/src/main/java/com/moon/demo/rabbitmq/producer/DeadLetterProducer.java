package com.moon.demo.rabbitmq.producer;

import com.moon.demo.rabbitmq.config.DeadLetterConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:DeadLetterProducer
 * @Author：xiaojiaxin
 * @Date：2020-02-18 11:50
 */
@Component
public class DeadLetterProducer implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg){
        //开启confim机制，需要实现RabbitTemplate.ConfirmCallback接口
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertSendAndReceive(DeadLetterConfig.BUSINESS_EXCHANGE_NAME, "", msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 这里的ack是Broker对发布者消息达到服务端的确认
        System.out.println("callbakck confirm: " + correlationData.getId() + " ACK : " + ack + " cause : "+ cause);
    }
}
