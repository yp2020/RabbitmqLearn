package com.study.consumer.receiver;

import com.study.consumer.config.HeaderConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/27 09:17
 **/
@Component
public class HeaderMsgReceiver {

    @RabbitListener(queues = HeaderConfig.HEADER_QUEUE_NAME_NAME)
    public void nameMsgHandler(byte[]msg){
        System.out.println("nameMsgHandler>>>>>" + new String(msg, 0, msg.length));
    }

    @RabbitListener(queues = HeaderConfig.HEADER_QUEUE_AGE_NAME)
    public void ageMsgHandler(byte[]msg){
        System.out.println("ageMsgHandler>>>>>" + new String(msg, 0, msg.length));
    }
}