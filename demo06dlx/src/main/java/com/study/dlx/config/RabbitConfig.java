package com.study.dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author yang
 * @date 2022/02/27 15:23
 * 普通的消息队列的配置
 **/

@Configuration
public class RabbitConfig {

    public static final String MSG_EXCHANGE_NAME="msg_exchange_name";
    public static final String MSG_QUEUE_NAME="msg_queue_name";

    @Bean
    Queue msgQueue(){
        HashMap<String,Object> args=new HashMap<>();
        //设置消息过期时间
        args.put("x-message-ttl",0);

        //指定死信队列的交换机
        args.put("x-dead-letter-exchange",RabbitDlxConfig.DLX_EXCHANGE_NAME);

        // 设置 死信消息 路由的 routing key
        args.put("x-dead-letter-routing-key",RabbitDlxConfig.DLX_QUEUE_NAME);
        return new Queue(MSG_QUEUE_NAME,true,false,false,args);
    }

    @Bean
    DirectExchange msgDirectExchange(){
        return new DirectExchange(MSG_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding msgQueueBinding(){
        return  BindingBuilder
                .bind(msgQueue())
                .to(msgDirectExchange())
                .with(MSG_QUEUE_NAME);
    }

}