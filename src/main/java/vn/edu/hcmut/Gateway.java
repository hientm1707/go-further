package vn.edu.hcmut;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class Gateway {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${MONGO_CLOUD_URI}")
    static String uri;

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
//        System.out.println(System.getenv().get("MONGO_CLOUD_URI"));
        System.out.println("Gateway is ready");
    }

}