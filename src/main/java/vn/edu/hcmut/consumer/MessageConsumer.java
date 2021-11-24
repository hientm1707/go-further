package vn.edu.hcmut.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmut.constant.QueueConstant;
import vn.edu.hcmut.dto.MessageDTO;

@Component
public class MessageConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = QueueConstant.QUEUE1)
    public void consumeMessageFromQueue(Message messageDTO) {
        System.out.println("======Received a message from gateway, sending ACK====");
        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY2, messageDTO.toString());
    }
}
