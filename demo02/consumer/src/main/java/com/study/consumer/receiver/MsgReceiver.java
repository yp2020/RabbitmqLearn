package com.study.consumer.receiver;

import com.rabbitmq.client.Channel;
import com.study.consumer.config.RabbitConfig;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yang
 * @date 2022/02/26 15:58
 **/
@Component
public class MsgReceiver {
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleMsg(Message message, Channel channel ) throws IOException {
        System.out.println("消费者1:" +message.getPayload());
        //告诉 mq 这条消息已经消费成功了
        channel.basicAck((Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),false);

    }

    /**
     * concurrency 为并发数量，也就是开启 10个值线程去消费消息
     * @param
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME,concurrency = "10")
    public void handleMsg2(Message message, Channel channel ) throws IOException {
     //   System.out.println("消费者1:" +message.getPayload());
        // 拒绝消费消息，true 表示 被拒绝的消息是否要重新回到队列中
        channel.basicReject((Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),true);
    }
}