package vn.edu.hcmut.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmut.constant.QueueConstant;

@Component
public class MessageConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QueueConstant.QUEUE1)
    public void consumeMessageFromQueue(Message messageDTO) throws InterruptedException {
        System.out.println("======Received a message from gateway, sending ACK====");
        Thread.sleep(3000);
        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY2, messageDTO.toString());
    }
}
