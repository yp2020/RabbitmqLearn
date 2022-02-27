package com.study.dlx.consumer;

import com.study.dlx.config.RabbitDlxConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yang
 * @date 2022/02/27 15:31
 * 消息正常发送,到消息队列,但是这个消息队列我们没有设置消费者,所以就过期了
 * 被转发至死信队列,这里配置 1 个死信队列的消费者
 **/
@Component
public class dlxConsumer {
    @RabbitListener(queues = RabbitDlxConfig.DLX_QUEUE_NAME)
    public void handle(String msg){
        System.out.println("从死信队列中消费的消息" + msg);
    }
}