package vn.edu.hcmut.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmut.constant.QueueConstant;
import vn.edu.hcmut.dto.CalculatorDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class GatewayService {

    private CompletableFuture<String> future;

    private String getConcatedStringWithOperator(String original, String op) throws JsonProcessingException {
        var node = new ObjectMapper().readTree(original);
        ((ObjectNode) node).put("op", op);
        return new ObjectMapper().writeValueAsString(node);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private static ExecutorService executor = Executors.newSingleThreadExecutor();


    @RabbitListener(queues = QueueConstant.QUEUE2)
    public void consumeMessageFromQueue(Message messageDTO) throws ExecutionException, InterruptedException {
        future.complete(messageDTO.toString());
    }

//    public CompletableFuture<String> getResponseBack(MessageDTO dto) {
//        CompletableFuture<String> future = new CompletableFuture<>();
//        this.future = future;
//        executor.submit(() -> {
//            rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY1, dto);
//            return null;
//        });
//        return future;
//    }


    public CompletableFuture<String> getCalResult(CalculatorDTO dto, String op) {
        CompletableFuture<String> future = new CompletableFuture<>();
        this.future = future;
        executor.submit(() -> {
            rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY1, getConcatedStringWithOperator(new ObjectMapper().writeValueAsString(dto), op));
            return null;
        });
        return this.future;
    }


}
