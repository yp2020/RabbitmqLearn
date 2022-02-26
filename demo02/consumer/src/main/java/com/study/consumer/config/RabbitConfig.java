package com.study.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/26 15:57
 **/
@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME="hello_world_queue";

    @Bean
    Queue helloWorldQueue(){
        return new Queue(QUEUE_NAME,true,false,false);

    }
}