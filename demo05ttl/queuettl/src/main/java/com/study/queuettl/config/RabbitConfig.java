package com.study.queuettl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author yang
 * @date 2022/02/27 14:59
 * 设置队列的过期时间
 **/
@Configuration
public class RabbitConfig {

    public static final String QUEUE_DELAY_QUEUE_NAME="queue_delay_queue_name";

    public static  final String QUEUE_DELAY_EXCHANGE_NAME="queue_delay_exchange_name";
    
    @Bean
    Queue messageDelayQueue(){
        HashMap<String,Object> args=new HashMap<>();
        // 给消息队列设置过期时间,该队列中得到消息如果 10s 内没人消费，则过期
        args.put("x-message-ttl",10000);
        return new Queue(QUEUE_DELAY_QUEUE_NAME,true,false,false,args);
    }

    @Bean
    DirectExchange messageDelayExchange(){
        return new DirectExchange(QUEUE_DELAY_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding messageDelayQueueBinding(){
        return BindingBuilder
                .bind(messageDelayQueue())
                .to(messageDelayExchange())
                .with(QUEUE_DELAY_QUEUE_NAME);
    }

}