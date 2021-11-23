package vn.com.momo.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.momo.constant.QueueConstant;
import vn.com.momo.dto.MessageDTO;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO dto) {
        rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY, QueueConstant.QUEUE_NAME);
    }
}
