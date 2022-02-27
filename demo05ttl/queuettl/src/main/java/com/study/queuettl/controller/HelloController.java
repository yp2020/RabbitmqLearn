package com.study.queuettl.controller;

import com.study.queuettl.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/27 15:04
 **/
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_DELAY_EXCHANGE_NAME, RabbitConfig.QUEUE_DELAY_QUEUE_NAME,"hello,这个是一条消息");
    }
}