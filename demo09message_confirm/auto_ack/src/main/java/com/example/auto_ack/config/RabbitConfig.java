package com.example.auto_ack.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/28 15:48
 **/
@Configuration
public class RabbitConfig {
    public static  final String MESSAGE_QUEUE_NAME="message_queue_name";
    public static  final String MESSAGE_EXCHANGE_NAME ="message_exchange_name";

    @Bean
    Queue messageQueue(){

        return new Queue(MESSAGE_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange messageExchange(){
        return new DirectExchange(MESSAGE_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding messageBinding(){
        return BindingBuilder
                .bind(messageQueue())
                .to(messageExchange())
                .with(MESSAGE_QUEUE_NAME);
    }

}