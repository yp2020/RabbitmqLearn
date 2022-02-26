package com.study.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/26 21:58
 **/
@Configuration
public class FanoutConfig {

    public static final String FANOUT_QUEUE_NAME1="fanout_queue_name1";
    public static final String FANOUT_QUEUE_NAME2="fanout_queue_name2";
    public static final String FANOUT_EXCHANGE_NAME="fanout_exchange_name";

    @Bean
    Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE_NAME1,true,false,false);
    }

    @Bean
    Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE_NAME2,true,false,false);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding fanoutBinding1(){
        return BindingBuilder
                .bind(fanoutQueue1())
                .to(fanoutExchange());
    }

    @Bean
    Binding fanoutBinding2(){
        return BindingBuilder
                .bind(fanoutQueue2())
                .to(fanoutExchange());
    }

}