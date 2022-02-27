package com.example.tx.cofig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author yang
 * @date 2022/02/27 17:29
 **/
@Configuration
public class RabbitConfig {

    public static  final String MESSAGE_QUEUE_NAME="message_queue_name";
    public static  final String MESSAGE_EXCHANGE_NAME="message_exchange_name";


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

    //事务管理器
    @Bean
    RabbitTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new RabbitTransactionManager(connectionFactory);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        // 开启事务模式
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

}