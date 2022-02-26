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
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME,"hello world 模式下发送消息 ");

    }

}
