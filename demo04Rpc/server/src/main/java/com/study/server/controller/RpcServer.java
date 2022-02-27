package com.study.server.controller;

import com.study.server.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/27 11:22
 **/
@Component
public class RpcServer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.RPC_MSG_QUEUE)
    public void process(Message message){
        byte[] messageBody = message.getBody();
        // 服务端要返回的消息
        Message replyMsg = MessageBuilder
                .withBody(("服务端返回的消息如下:" + new String(messageBody))
                        .getBytes()).build();
        CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());

        rabbitTemplate.sendAndReceive(RabbitConfig.RPC_EXCHANGE,RabbitConfig.RPC_REPLY_MSG_QUEUE,replyMsg,correlationData);

    }
}