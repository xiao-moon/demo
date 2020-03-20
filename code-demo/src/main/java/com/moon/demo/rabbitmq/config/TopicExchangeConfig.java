package com.moon.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:TopicExchange是按规则转发消息，是交换机中最灵活的一个。也是最常用的一个。
 * @Author：xiaojiaxin
 * @Date：2020-02-18 2:03
 */
@Configuration
public class TopicExchangeConfig {

    /**
     * 配置一个routingKey为api.core的消息队列并绑定在coreExchange交换机上（交换机的匹配规则为api.core.*）
     */
    @Bean
    // org.springframework.amqp.core.Queue
    public Queue coreQueue() {
        return new Queue("api.core");
    }

    /**
     * 配置一个routingKey为api.payment的消息队列并绑定在paymentExchange交换机上（交换机的匹配规则为api.payment.#）
     */
    @Bean
    public Queue paymentQueue() {
        return new Queue("api.payment");
    }

    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }

}
