package com.moon.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:FanoutExchange交换机是转发消息到所有绑定队列（广播模式，和routingKey没有关系）。
 * @Author：xiaojiaxin
 * @Date：2020-02-18 2:29
 */
@Configuration
public class FanoutExchangeConfig {

    /**
     * 配置一个routingKey为api.report.payment的消息队列并绑定在reportExchange交换机上
     */
    @Bean
    public Queue reportPaymentQueue() {
        return new Queue("api.report.payment");
    }

    /**
     * 配置一个routingKey为api.report.refund的消息队列并绑定在reportExchange交换机上
     */
    @Bean
    public Queue reportRefundQueue() {
        return new Queue("api.report.refund");
    }

    @Bean
    public FanoutExchange reportExchange() {
        return new FanoutExchange("reportExchange");
    }

    @Bean
    public Binding bindingReportPaymentExchange(Queue reportPaymentQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportPaymentQueue).to(reportExchange);
    }

    @Bean
    public Binding bindingReportRefundExchange(Queue reportRefundQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportRefundQueue).to(reportExchange);
    }




}
