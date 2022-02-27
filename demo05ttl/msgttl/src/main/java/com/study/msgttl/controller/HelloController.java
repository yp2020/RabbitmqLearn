package com.study.msgttl.controller;

import com.study.msgttl.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/27 14:42
 **/
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(){
        Message msg= MessageBuilder
                .withBody("hello Body!!".getBytes())
                //设置过期时间为 10s  消息到达消息队列之后, 10s 内没有消费者,则消息就会过期
                .setExpiration("10000")
                .build();
        rabbitTemplate.send(RabbitConfig.MESSAGE_DELAY_EXCHANGE_NAME,RabbitConfig.MESSAGE_DELAY_QUEUE_NAME,msg);
    }
}