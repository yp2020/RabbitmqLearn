package com.study.message_delay_plugin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


/**
 * @author yang
 * @date 2022/02/27 16:04
 **/
@Configuration
public class RabbitConfig {

    public static  final String DELAY_MSG_QUEUE_NAME="delay_msg_queue_name";
    public static  final String DELAY_MSG_EXCHANGE_NAME="delay_msg_exchange_name";

    @Bean
    Queue delayQueue(){
        return new Queue(DELAY_MSG_QUEUE_NAME,true,false,false);
    }

    /**
     * 这里的交换机 可以通过 args 来实现自定义
     * 可以用任意类型的交换机
     *
     * @return
     */
    @Bean
    CustomExchange customExchange(){
        HashMap<String ,Object> args=new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange(DELAY_MSG_EXCHANGE_NAME,"x-delayed-message",true,false,args);
    }

    @Bean
    Binding delayedBinding(){
        return BindingBuilder
                .bind(delayQueue())
                .to(customExchange())
                .with(DELAY_MSG_QUEUE_NAME)
                .noargs();
    }

}