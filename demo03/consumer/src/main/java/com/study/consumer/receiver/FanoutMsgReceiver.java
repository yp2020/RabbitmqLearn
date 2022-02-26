package com.study.consumer.receiver;

import com.study.consumer.config.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/26 21:56
 **/
@Component
public class FanoutMsgReceiver {

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME1)
    public void mshHandler(String msg){
        System.out.println("FanoutReceiver1 " + msg);
    }

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_NAME2)
    public void mshHandler2(String msg){
        System.out.println("FanoutReceiver2 " + msg);
    }
}