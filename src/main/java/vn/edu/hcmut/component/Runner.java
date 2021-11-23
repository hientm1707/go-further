//package vn.edu.hcmut.component;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import static vn.edu.hcmut.constant.QueueConstant.EXCHANGE_NAME;
//import static vn.edu.hcmut.constant.QueueConstant.ROUTING_KEY;
//
//@Component
//public class Runner implements CommandLineRunner {
//    private final RabbitTemplate rabbitTemplate;
//
//    public Runner(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, "Hello from RabbitMQ!");
//    }
//}
////