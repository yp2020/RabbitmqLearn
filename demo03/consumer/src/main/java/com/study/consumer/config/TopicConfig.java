package com.study.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/27 08:03
 * 在 TopicExchange 中，Queue 通过 routingkey 绑定到 TopicExchange 上，
 * 当消息到达 TopicExchange 后，
 * TopicExchange 根据消息的 routingkey 将消息路由到一个或者多个 Queue 上。
 * 各个队列可以订阅自己感兴趣的消息。
 **/

@Configuration
public class TopicConfig {

    public static  final String TOPIC_EXCHANGE_NAME= "topic_exchange_name";
    public static  final String XIAOMI_QUEUE_NAME= "xiaomi_queue_name";
    public static  final String HUAWEI_QUEUE_NAME= "huawei_queue_name";
    public static  final String PHONE_QUEUE_NAME= "phone_queue_name";

    @Bean
    Queue xiaomiQueue(){
        return new Queue(XIAOMI_QUEUE_NAME,true,false,false);
    }

    @Bean
    Queue huaweiQueue(){
        return new Queue(HUAWEI_QUEUE_NAME,true,false,false);
    }

    @Bean
    Queue phoneQueue(){
        return new Queue(PHONE_QUEUE_NAME,true,false,false);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding xiaomiBinding(){
        return BindingBuilder
                .bind(xiaomiQueue())
                .to(topicExchange())
                // # 为通配符，表示将来的消息只要是以 xiaomi 开头，都将被路由到 xiaomiQueue
                .with("xiaomi.#");
    }

    @Bean
    Binding huaweiBinding(){
        return BindingBuilder
                .bind(huaweiQueue())
                .to(topicExchange())
                // # 为通配符
                .with("huawei.#");
    }
    @Bean
    Binding phoneBinding(){
        return BindingBuilder
                .bind(phoneQueue())
                .to(topicExchange())
                // # 为通配符号
                .with("#.phone.#");
    }



}