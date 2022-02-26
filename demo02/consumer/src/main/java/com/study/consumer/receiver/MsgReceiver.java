package com.study.consumer.receiver;

import com.study.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/26 15:58
 **/
@Component
public class MsgReceiver {
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMsg(String msg){
        System.out.println("msg: "+msg);
    }
}