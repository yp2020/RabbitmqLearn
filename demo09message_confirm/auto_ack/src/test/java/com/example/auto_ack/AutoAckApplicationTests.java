package com.example.auto_ack;

import com.example.auto_ack.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class AutoAckApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
        long deliveryTag = 0L;
        try {
            GetResponse getResponse = channel.basicGet(RabbitConfig.MESSAGE_QUEUE_NAME, false);
            deliveryTag = getResponse.getEnvelope().getDeliveryTag();
            System.out.println("o = " + new String((getResponse.getBody()), "UTF-8"));
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
