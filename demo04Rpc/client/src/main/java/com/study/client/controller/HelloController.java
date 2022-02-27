package com.study.client.controller;

import com.study.client.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/27 10:20
 **/
@RestController
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello(String message){
        // 要发送的消息对象
        Message msg= MessageBuilder
                .withBody(message.getBytes())
                .build();

        // 发送消息，方法的返回值就是 server 给出的响应
        Message result = rabbitTemplate.
                sendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_MSG_QUEUE, msg);

        if(result!=null){
            //获取发送的消息的 correlationId 发送消息的时候会自动加上这个 correlationId
            String correlationId = msg.getMessageProperties().getCorrelationId();

            //返回的消息也有这个 correlationId
            String spring_returned_message_correlation= (String)result.getMessageProperties().getHeaders().get("spring_returned_message_correlation");

            if(correlationId.equals(spring_returned_message_correlation)){
                System.out.println("收到服务端的响应 " + new String(result.getBody()));
            }
        }

    }
}