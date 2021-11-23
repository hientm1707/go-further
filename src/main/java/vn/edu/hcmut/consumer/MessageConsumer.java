package vn.edu.hcmut.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vn.edu.hcmut.constant.QueueConstant;
import vn.edu.hcmut.dto.MessageDTO;

@Component
public class MessageConsumer {
    @RabbitListener(queues = QueueConstant.QUEUE_NAME)
    public void consumeMessageFromQueue(MessageDTO messageDTO) {
        System.out.println("Received from queue :" + messageDTO.toString());
    }
}
