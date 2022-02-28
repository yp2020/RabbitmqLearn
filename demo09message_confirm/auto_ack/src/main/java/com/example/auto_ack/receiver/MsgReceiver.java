package com.example.auto_ack.receiver;

import com.example.auto_ack.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/28 15:48
 **/
@Component
public class MsgReceiver {
    private static  final Logger logger=LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitListener(queues= RabbitConfig.MESSAGE_QUEUE_NAME)
    public void msgHandle(String msg){
        logger.info("msg: {}",msg);
    }

}