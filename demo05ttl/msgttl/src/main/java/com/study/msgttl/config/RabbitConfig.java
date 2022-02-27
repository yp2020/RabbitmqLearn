package com.study.msgttl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/27 14:34
 * direct 交换机
 *
 **/
@Configuration
public class RabbitConfig {
    public static final String MESSAGE_DELAY_QUEUE_NAME="message_delay_queue_name";

    public static  final String MESSAGE_DELAY_EXCHANGE_NAME="message_delay_exchange_name";

    @Bean
    Queue messageDelayQueue(){
        return new Queue(MESSAGE_DELAY_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange messageDelayExchange(){
        return new DirectExchange(MESSAGE_DELAY_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding messageDelayQueueBinding(){
        return BindingBuilder
                .bind(messageDelayQueue())
                .to(messageDelayExchange())
                .with(MESSAGE_DELAY_QUEUE_NAME);
    }

}