package com.study.publisher.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/26 15:51
 * hello world 模式
 * 不用自己写交换机，使用默认的即可
 **/
@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME="hello_world_queue";

    @Bean
    Queue helloWorldQueue(){
        return new Queue(QUEUE_NAME,true,false,false);

    }
}