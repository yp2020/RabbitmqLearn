package com.study.publisher;

import com.study.publisher.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    void contextLoads() {
        //

        for(int i=1;i<=20;i++){
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME,"work queue 模式下发送的第"+i+"条消息, ");
        }
    }

}
