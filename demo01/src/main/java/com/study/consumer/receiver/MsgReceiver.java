package com.study.consumer.receiver;

import com.study.consumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/26 11:14
 **/
@Component
public class MsgReceiver {

    /**
     * 通过 rabbitmq 注解 指定该方法监听的消息队列，
     * 该注解的参数就是消息队列名字
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMsg(String msg){
        System.out.println("msg: "+msg);
    }
}