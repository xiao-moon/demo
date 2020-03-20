package com.moon.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description:direct exchange(直连型交换机)
 * @Author：xiaojiaxin
 * @Date：2020-02-17 18:43
 */
@Configuration
public class DirectExchangeConfig {

    /**
     * DirectExchange
     * 配置一个routingKey为notify.payment的消息队列
     */
    @Bean
    public Queue testQueue() {
        return new Queue("notify.payment");
    }



}
