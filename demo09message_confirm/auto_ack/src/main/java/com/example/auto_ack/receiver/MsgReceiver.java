package com.example.auto_ack.receiver;

import com.example.auto_ack.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yang
 * @date 2022/02/28 15:48
 **/
//@Component
public class MsgReceiver {
    private static  final Logger logger=
            LoggerFactory.getLogger(MsgReceiver.class);

    @RabbitListener(queues= RabbitConfig.MESSAGE_QUEUE_NAME)
    public void handle3(Message message, Channel channel) {
        // 获取消息的一个标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //消息消费的代码写到这里

            String s = new String(message.getBody());
            System.out.println("s = " + s);
            //消费完成后，手动 ack
            /**
             * 第一个参数为 消息的标记
             * 第二个参数 如果为 false 表示只确认当前消息
             */
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            //手动 nack 告诉 mq 这条消息消费失败了
            try {
                /**
                 * 最后一个参数 是否重新入队
                 */
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}