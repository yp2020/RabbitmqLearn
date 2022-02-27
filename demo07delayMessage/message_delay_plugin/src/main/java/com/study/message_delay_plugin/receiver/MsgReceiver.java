package com.study.message_delay_plugin.receiver;

import com.study.message_delay_plugin.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/27 16:18
 **/
@Component
public class MsgReceiver {

    @RabbitListener(queues = RabbitConfig.DELAY_MSG_QUEUE_NAME)
    public void handle(String msg){
        System.out.println("msg= "  + msg);
    }
}