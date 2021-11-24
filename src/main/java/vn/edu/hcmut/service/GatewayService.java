package vn.edu.hcmut.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.hcmut.constant.QueueConstant;
import vn.edu.hcmut.dto.MessageDTO;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class GatewayService {

    private CompletableFuture<String> future;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public CompletableFuture<String> getResponseBack(@RequestBody @Valid MessageDTO dto) {
        CompletableFuture<String> future = new CompletableFuture<>();
        this.future = future;
        executor.submit(() -> {
            rabbitTemplate.convertAndSend(QueueConstant.EXCHANGE_NAME, QueueConstant.ROUTING_KEY1, dto);
            return null;
        });
        return future;
    }


    @RabbitListener(queues = QueueConstant.QUEUE2)
    public void consumeMessageFromQueue(Message messageDTO) throws ExecutionException, InterruptedException {
        future.complete(messageDTO.toString());
    }

}
