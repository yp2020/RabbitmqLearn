package com.study.consumer.receiver;

import com.study.consumer.config.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/27 08:14
 **/
@Component
public class TopicMsgReceiver {

    @RabbitListener(queues = TopicConfig.HUAWEI_QUEUE_NAME)
    public void huawei(String msg){
        System.out.println("huawei " + msg);
    }

    @RabbitListener(queues = TopicConfig.XIAOMI_QUEUE_NAME)
    public void xiaomi(String msg){
        System.out.println("xiaomi " + msg);
    }
    @RabbitListener(queues = TopicConfig.PHONE_QUEUE_NAME)
    public void phone(String msg){
        System.out.println("phone " + msg);
    }
}