package vn.edu.hcmut.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmut.constant.QueueConstant;
import vn.edu.hcmut.dto.FullCalculatorDTO;

@Component
public class MessageConsumer {

    private Integer calculateExpression(FullCalculatorDTO dto) throws InterruptedException {
        var op = dto.getOp();
        var o1 = dto.getO1();
        var o2 = dto.getO2();
        switch (op) {
            case "plus":
                return o1 + o2;
            case "minus":
                Thread.sleep(3000);
                return o1 - o2;
            case "multiply":
                return o1 * o2;
            case "devide":
                return o1 / o2;
        }
        return Integer.MAX_VALUE;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @RabbitListener(queues = QueueConstant.QUEUE1)
//    public void consumeMessageFromQueue(Message messageDTO) {
//        System.out.println("======Received a message from gateway, sending ACK====");
//        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY2, messageDTO.toString());
//    }


    @RabbitListener(queues = QueueConstant.QUEUE1)
    public void consumeCalculatorDTO(Message message) throws JsonProcessingException, InterruptedException {
        System.out.println("======Received a Calculator message from gateway, sending ACK====");
        String concatTemp = message.toString().substring(7);
        String body = concatTemp.substring(0, concatTemp.indexOf("M") - 2);
        var realBody = body.substring(1, body.length() - 1).replace("\\", "");
        var dto = new ObjectMapper().readValue(realBody, FullCalculatorDTO.class);
        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY2, calculateExpression(dto));
    }

    public void consumeMessageFromQueue(Message messageDTO) throws InterruptedException {
        System.out.println("======Received a message from gateway, sending ACK====");
        Thread.sleep(3000);
        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY2, messageDTO.toString());
    }


}
