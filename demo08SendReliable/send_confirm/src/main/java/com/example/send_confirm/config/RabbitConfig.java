package com.example.send_confirm.config;

import com.rabbitmq.client.ConfirmCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author yang
 * @date 2022/02/27 21:02
 * 定义配置类
 * 实现 RabbitTemplate.ConfirmCallback 和 RabbitTemplate.ReturnsCallback 两个接口，
 * 这两个接口，前者的回调用来确定消息到达交换器，后者则会在消息路由到队列失败时被调用。
 * 定义 initRabbitTemplate 方法并添加 @PostConstruct 注解,
 * 在该方法中为 rabbitTemplate 分别配置这两个 Callback。
 **/
@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    public static  final String FASONG_QUEUE_NAME="fasong_queue_name";
    public static  final String FASONG_EXCHANGE_NAME ="fasong_exchange_name";
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setReturnsCallback(this::returnedMessage);
        rabbitTemplate.setConfirmCallback(this::confirm);
    }

    @Bean
    Queue messageQueue(){

        return new Queue(FASONG_QUEUE_NAME,true,false,false);
    }

    @Bean
    DirectExchange messageExchange(){
        return new DirectExchange(FASONG_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding messageBinding(){
        return BindingBuilder
                .bind(messageQueue())
                .to(messageExchange())
                .with(FASONG_QUEUE_NAME);
    }


    /**
     * 消息到达交换机 回调
     * @param correlationData
     * @param ack true 表示消息成功到达交换机 false 表示失败
     * @param cause 原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            logger.info("{} 消息成功到达交换机",correlationData.getId());
        }else{
            logger.error("{} 消息未到达交换机, {}",correlationData.getId(),cause);
        }
    }

    /**
     * 消息没有到达 队列的回调
     * @param returned
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        logger.error("{}:消息未成功路由到队列",
                returned.getMessage().getMessageProperties().getMessageId());
    }
}