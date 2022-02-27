package com.study.message_delay_plugin.controller;

import com.study.message_delay_plugin.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yang
 * @date 2022/02/27 16:20
 **/
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(){
        Message message = MessageBuilder.withBody(("hello 江南一点雨" + new Date()).getBytes())
                //设置消息发送的延迟时间
                .setHeader("x-delay", 3000).build();

        rabbitTemplate.send(RabbitConfig.DELAY_MSG_EXCHANGE_NAME,RabbitConfig.DELAY_MSG_QUEUE_NAME,message);

    }
}