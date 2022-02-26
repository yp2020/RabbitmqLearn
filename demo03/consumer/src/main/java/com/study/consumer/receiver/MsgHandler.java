package com.study.consumer.receiver;

import com.study.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/26 17:48
 **/
@Component
public class MsgHandler {

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_NAME)
    public void msgHandler(String msg){
        System.out.println("msg1 "+msg);

    }

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE_NAME2)
    public void msgHandler2(String msg){
        System.out.println("msg2 "+msg);

    }
}