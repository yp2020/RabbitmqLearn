package com.study.message_delay_dlx.consumer;

import com.study.message_delay_dlx.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yang
 * @date 2022/02/27 16:45
 **/
@Component
public class msgReceiver {

    @RabbitListener(queues = RabbitConfig.DELAY_QUEUE_NAME)
    public void handle(String msg) {
        System.out.println("死信队列做延迟消息队列: " +(new Date())+" "+msg);
    }
}