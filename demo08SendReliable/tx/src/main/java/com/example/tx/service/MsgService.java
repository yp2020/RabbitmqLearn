package com.example.tx.service;

import com.example.tx.cofig.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yang
 * @date 2022/02/27 20:49
 **/
@Service
public class MsgService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    public void send(){
        rabbitTemplate.convertAndSend(RabbitConfig.MESSAGE_EXCHANGE_NAME,RabbitConfig.MESSAGE_QUEUE_NAME,"hello,message");


    }
}