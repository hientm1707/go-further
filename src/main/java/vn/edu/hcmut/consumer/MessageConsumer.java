package vn.edu.hcmut.consumer;

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
    public void consumeMessageFromQueue(MessageDTO messageDTO) {
        System.out.println("Received from queue :" + messageDTO.toString());
        System.out.println("PROCESSSING BACK TO QUEUE");
        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY2,
                messageDTO
                .builder()
                .message("Message from service")
                .status(123)
                .build()
        );
    }
}
