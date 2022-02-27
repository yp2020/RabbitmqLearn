package com.study.message_delay_dlx.controller;

import com.study.message_delay_dlx.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * @author yang
 * @date 2022/02/27 16:44
 **/
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(){
        Message message = MessageBuilder.withBody(("死信队列做延迟队列" + new Date()).getBytes()) .build();
        rabbitTemplate.send(
                RabbitConfig.MESSAGE_EXCHANGE_NAME,
                RabbitConfig.MESSAGE_QUEUE_NAME,
                message);
    }
}