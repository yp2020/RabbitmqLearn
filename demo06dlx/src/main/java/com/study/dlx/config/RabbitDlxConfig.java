package com.study.dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/27 15:16
 * 死信队列， 也就是一个普通的消息队列
 **/

@Configuration
public class RabbitDlxConfig {

    public static final String DLX_EXCHANGE_NAME="dlx_exchange_name";
    public static final String DLX_QUEUE_NAME="dlx_queue_name";

    @Bean
    Queue dlxQueue(){
        return new Queue(DLX_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange dlxDirectExchange(){
        return new DirectExchange(DLX_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding dlxQueueBinding(){
       return  BindingBuilder
                .bind(dlxQueue())
                .to(dlxDirectExchange())
                .with(DLX_QUEUE_NAME);
    }

}