//package vn.edu.hcmut.consumer;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import vn.edu.hcmut.constant.QueueConstant;
//
//
//@Component
//public class MessageConsumer {
//
//    @RabbitListener(queues = QueueConstant.QUEUE2)
//    public void consumeMessageFromQueue(Message messageDTO) {
//        System.out.println("Received from service :" + messageDTO.toString());
//    }
//}
