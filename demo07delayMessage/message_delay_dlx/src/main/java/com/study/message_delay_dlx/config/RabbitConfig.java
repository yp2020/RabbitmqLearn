package com.study.message_delay_dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author yang
 * @date 2022/02/27 16:36
 **/
@Configuration
public class RabbitConfig {
    public static  final String MESSAGE_QUEUE_NAME="message_queue_name";
    public static  final String MESSAGE_EXCHANGE_NAME="message_exchange_name";

    public static final String DELAY_EXCHANGE_NAME ="delay_exchange_name";
    public static final String DELAY_QUEUE_NAME ="delay_queue_name";

    @Bean
    Queue messageQueue(){
        HashMap<String,Object> args=new HashMap<>();
        //设置消息过期时间
        args.put("x-message-ttl",5000);
        //指定死信队列的交换机
        args.put("x-dead-letter-exchange",RabbitConfig.DELAY_EXCHANGE_NAME);
        // 设置 死信消息 路由的 routing key
        args.put("x-dead-letter-routing-key",RabbitConfig.DELAY_QUEUE_NAME);

        return new Queue(MESSAGE_QUEUE_NAME,true,false,false,args);
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

    //

    @Bean
    Queue dlxQueue(){
        return new Queue(DELAY_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange dlxExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding dlxBinding(){
        return BindingBuilder
                .bind(dlxQueue())
                .to(dlxExchange())
                .with(DELAY_QUEUE_NAME);
    }
}