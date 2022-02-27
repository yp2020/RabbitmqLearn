package com.study.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/27 11:21
 **/

@Configuration
public class RabbitConfig {

    // 消息发送的队列
    public static final  String RPC_MSG_QUEUE="rpc_msg_queue";
    // 消息接收的队列
    public static  final String RPC_REPLY_MSG_QUEUE="rpc_reply_msg_queue";
    //交换机
    public  static  final String RPC_EXCHANGE ="rpc_exchange";

    // 发消息 就把消息往 msg 这个队列里面发
    @Bean
    Queue msgQueue(){
        return new Queue(RPC_MSG_QUEUE);
    }

    // 发送消息之后，
    // 对方接收之后，会返回一个响应，就从这个队列里面取出来
    @Bean
    Queue replyQueue(){
        return new Queue(RPC_REPLY_MSG_QUEUE);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(RPC_EXCHANGE);
    }

    @Bean
    Binding msgBinding(){
        return BindingBuilder
                .bind(msgQueue())
                .to(topicExchange())
                // 名字做 routing key
                .with(RPC_MSG_QUEUE);
    }

    @Bean
    Binding replyBinding(){
        return BindingBuilder
                .bind(replyQueue())
                .to(topicExchange())
                // 名字做 routing key
                .with(RPC_REPLY_MSG_QUEUE);
    }


}