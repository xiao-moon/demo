package com.moon.demo.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-17 21:47
 */
@Component
//@RabbitListener是用来绑定队列的，
// 该接收者绑定了QUEUE这个队列，下面的@RabbitHandler注解是用来表示该方法是接收者接收消息的方法。
//@RabbitListener(queues="QUEUE")
public class DirectConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
     * @RabbitListener 标注在类上面表示当有收到消息的时候，
     * 就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
     */
//    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "topic.exchange",durable = "true",type = "topic"),
            value = @Queue(value = "consumer_queue",durable = "true"),
            key = "key.#"
    ))
    public void receive(Message message, Channel channel) throws IOException {
        logger.info("notify.payment receive message: "+new String(message.getBody()));
        // 使用时需要在application.properties开启手动确认设置
        // 消息的标识，false只确认当前一个消息收到，true确认所有将比第一个参数指定的 delivery tag 小的consumer都获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
