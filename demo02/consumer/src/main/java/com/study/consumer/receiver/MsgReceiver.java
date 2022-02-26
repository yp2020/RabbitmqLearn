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
        System.out.println("消费者1 : "+msg);
    }

    /**
     * concurrency 为并发数量，也就是开启 10个值线程去消费消息
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME,concurrency = "10")
    public void handleMsg2(String msg){
        System.out.println("消费者2:  "+msg);
    }
}