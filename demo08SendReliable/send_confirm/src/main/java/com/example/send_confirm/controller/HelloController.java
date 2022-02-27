package com.example.send_confirm.controller;

import com.example.send_confirm.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yang
 * @date 2022/02/27 21:14
 **/
@RestController
public class HelloController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(){

        rabbitTemplate.convertAndSend(RabbitConfig.FASONG_EXCHANGE_NAME,RabbitConfig.FASONG_QUEUE_NAME,"发送方确认机制，来发消息",new CorrelationData(UUID.randomUUID().toString()));

    }
}