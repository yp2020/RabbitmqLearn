package com.study.publisher;

import com.study.publisher.config.FanoutConfig;
import com.study.publisher.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        // 交换机， routingKey 消息对象
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME,RabbitConfig.DIRECT_QUEUE_NAME,"这条消息发给队列1");
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE_NAME,RabbitConfig.DIRECT_QUEUE_NAME2,"这条消息发送给队列2");
    }

    @Test
    void fanoutTest(){
        rabbitTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE_NAME,null,"hello fanoutExchange");
    }

}
