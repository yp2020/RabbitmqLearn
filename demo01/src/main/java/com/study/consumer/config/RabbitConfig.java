package com.study.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author yang
 * @date 2022/02/26 11:05
 **/
@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME="myMessage_queue";

    @Bean
    Queue myQueue(){
        /**
         * 1. 队列名字
         * 2. durable 持久化
         * 3. exclusive 是否具有排他性，有排他性的队列只能被创建器的 Connection 处理
         * 4. autoDelete 如果该队列没有消费者，是否自动删除队列
         */
        return new Queue(QUEUE_NAME,true,false,false);
    }

}