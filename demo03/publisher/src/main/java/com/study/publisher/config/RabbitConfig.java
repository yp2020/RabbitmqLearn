package com.study.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 * @date 2022/02/26 17:51
 **/
@Configuration
public class RabbitConfig {

    public static  final String DIRECT_QUEUE_NAME="direct_queue_name";
    public static  final String DIRECT_QUEUE_NAME2="direct_queue_name2";
    public static final String DIRECT_EXCHANGE_NAME ="direct_exchange_name";



    @Bean
    Queue directQueue(){
        return new Queue(DIRECT_QUEUE_NAME,true,false,false);
    }

    @Bean
    Queue directQueue2(){

        return  new Queue(DIRECT_QUEUE_NAME2,true,false,false);
    }

    // 直连交换机
    @Bean
    DirectExchange directExchange(){
        /**
         * 1. 交换机的名字
         * 2. 交换机是否持久化，注意，这里是对交换机的持久化，交换机本身有参数，所以要考虑是否写到硬盘中去，
         *    也就是重启之后，交换机是否还要存在 true 就还在
         * 3. 没有队列是否要删除交换机

         */
        return new DirectExchange(DIRECT_EXCHANGE_NAME,true,false);
    }

    /**
     * 将交换机与队列绑定起来
     */
    @Bean
    Binding directBinding1(){
        return BindingBuilder
                // 设置绑定相关的队列
                .bind(directQueue())
                // 设置绑定的交换机
                .to(directExchange())
                // 设置 routing_key 这里用名字代替
                .with(DIRECT_QUEUE_NAME);
    }


    @Bean
    Binding directBinding2(){
        return BindingBuilder
                // 设置绑定相关的队列
                .bind(directQueue2())
                // 设置绑定的交换机
                .to(directExchange())
                // 设置 routing_key
                .with(DIRECT_QUEUE_NAME2);
    }

}