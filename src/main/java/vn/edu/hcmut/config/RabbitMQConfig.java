package vn.edu.hcmut.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.hcmut.constant.QueueConstant;


@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.username}")
    String username;
    @Value("${spring.rabbitmq.password}")
    String password;


    @Bean
    Queue queue1() {
        return new Queue(QueueConstant.QUEUE1, false);
    }

    @Bean
    Queue queue2() {
        return new Queue(QueueConstant.QUEUE2, false);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(QueueConstant.EXCHANGE_NAME);
    }

    @Bean
    Binding binding1(TopicExchange exchange) {
        return BindingBuilder.bind(queue1()).to(exchange).with(QueueConstant.ROUTING_KEY1);
    }


    @Bean
    Binding binding2(TopicExchange exchange) {
        return BindingBuilder.bind(queue2()).to(exchange).with(QueueConstant.ROUTING_KEY2);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
