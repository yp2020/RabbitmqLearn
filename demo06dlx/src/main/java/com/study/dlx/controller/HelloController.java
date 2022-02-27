package com.study.dlx.controller;

import com.study.dlx.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/27 15:28
 **/
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello() {
        rabbitTemplate.convertAndSend(RabbitConfig.MSG_EXCHANGE_NAME, RabbitConfig.MSG_QUEUE_NAME, "hello,这是一条消息");
    }
}