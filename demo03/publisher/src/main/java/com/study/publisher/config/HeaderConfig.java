package com.study.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/27 09:20
 **/
@Configuration
public class HeaderConfig {

    public static  final String HEADER_QUEUE_NAME_NAME="header_queue_name_name";
    public static  final String HEADER_QUEUE_AGE_NAME="header_queue_age_name";
    public static final String HEADER_EXCHANGE_NAME ="header_exchange_name";

    @Bean
    Queue headerNameQueue(){
        return new Queue(HEADER_QUEUE_NAME_NAME,true,false,false);
    }

    @Bean
    Queue headerAgeQueue(){
        return new Queue(HEADER_QUEUE_AGE_NAME,true,false,false);
    }

    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange(HEADER_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding nameBinding(){
        return BindingBuilder
                .bind(headerNameQueue())
                .to(headersExchange())
                // 如果将来消息头部中包含 name 属性，就算匹配成功
                .where("name").exists();
    }

    @Bean
    Binding ageBinding(){
        return BindingBuilder
                .bind(headerAgeQueue())
                .to(headersExchange())
                // 如果将来消息头部中包含 age 属性，并且属性值为 99
                .where("age")
                .matches(99);
    }



}