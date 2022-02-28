package com.example.auto_ack.controller;

import com.example.auto_ack.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/28 15:55
 **/
@RestController
public class HelloController {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.MESSAGE_EXCHANGE_NAME,RabbitConfig.MESSAGE_QUEUE_NAME,"hello,消息在这里");
    }
}