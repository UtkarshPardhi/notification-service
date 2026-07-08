package com.utkarsh.notification_service.config;



import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import com.utkarsh.notification_service.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // QUEUES
    @Bean
    public Queue emailQueue() {

        return QueueBuilder
                .durable(RabbitMQConstants.EMAIL_QUEUE)
                .deadLetterExchange(RabbitMQConstants.DLX_EXCHANGE)
                .deadLetterRoutingKey(RabbitMQConstants.EMAIL_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue smsQueue() {

        return QueueBuilder
                .durable(RabbitMQConstants.SMS_QUEUE)
                .deadLetterExchange(RabbitMQConstants.DLX_EXCHANGE)
                .deadLetterRoutingKey(RabbitMQConstants.SMS_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue pushQueue() {

        return QueueBuilder
                .durable(RabbitMQConstants.PUSH_QUEUE)
                .deadLetterExchange(RabbitMQConstants.DLX_EXCHANGE)
                .deadLetterRoutingKey(RabbitMQConstants.PUSH_DLQ_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue emailDeadLetterQueue() { return new Queue(RabbitMQConstants.EMAIL_DLQ);}

    @Bean
    public Queue smsDeadLetterQueue() { return new Queue(RabbitMQConstants.SMS_DLQ);}

    @Bean
    public Queue pushDeadLetterQueue() { return new Queue(RabbitMQConstants.PUSH_DLQ);}

//    @Bean
//    public Queue emailRetryQueue() {
//        return QueueBuilder.durable(RabbitMQConstants.EMAIL_RETRY_QUEUE)
//                .ttl(RabbitMQConstants.RETRY_DELAY)
//                .deadLetterExchange(RabbitMQConstants.NOTIFICATION_EXCHANGE)
//                .deadLetterRoutingKey(RabbitMQConstants.EMAIL_ROUTING_KEY)
//                .build();
//    }
//
//    @Bean
//    public Queue smsRetryQueue() {
//        return QueueBuilder.durable(RabbitMQConstants.SMS_RETRY_QUEUE)
//                .ttl(RabbitMQConstants.RETRY_DELAY)
//                .deadLetterExchange(RabbitMQConstants.NOTIFICATION_EXCHANGE)
//                .deadLetterRoutingKey(RabbitMQConstants.SMS_ROUTING_KEY)
//                .build();
//    }
//
//    @Bean
//    public Queue pushRetryQueue() {
//        return QueueBuilder.durable(RabbitMQConstants.PUSH_RETRY_QUEUE)
//                .ttl(RabbitMQConstants.RETRY_DELAY)
//                .deadLetterExchange(RabbitMQConstants.NOTIFICATION_EXCHANGE)
//                .deadLetterRoutingKey(RabbitMQConstants.PUSH_ROUTING_KEY)
//                .build();
//    }


    // EXCHANGE
    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(RabbitMQConstants.NOTIFICATION_EXCHANGE);
    }

    @Bean
    public TopicExchange deadLetterExchange() { return new TopicExchange(RabbitMQConstants.DLX_EXCHANGE);}

//    @Bean
//    public TopicExchange retryExchange(){ return new TopicExchange(RabbitMQConstants.RETRY_EXCHANGE);}


    // BINDINGS
    @Bean
    public Binding emailBinding(
            @Qualifier("emailQueue") Queue emailQueue,
                                TopicExchange notificationExchange){

        return BindingBuilder.bind(emailQueue)
                .to(notificationExchange)
                .with(RabbitMQConstants.EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding smsBinding(
            @Qualifier("smsQueue") Queue smsQueue,
                              TopicExchange notificationExchange){

        return BindingBuilder.bind(smsQueue)
                .to(notificationExchange)
                .with(RabbitMQConstants.SMS_ROUTING_KEY);
    }

    @Bean
    public Binding pushBinding(
            @Qualifier("pushQueue") Queue pushQueue,
                               TopicExchange notificationExchange){

        return BindingBuilder.bind(pushQueue)
                .to(notificationExchange)
                .with(RabbitMQConstants.PUSH_ROUTING_KEY);
    }

    @Bean
    public Binding emailDlqBinding(
            @Qualifier("emailDeadLetterQueue") Queue queue,
            @Qualifier("deadLetterExchange") TopicExchange Exchange){

        return BindingBuilder.bind(queue)
                .to(Exchange)
                .with(RabbitMQConstants.EMAIL_DLQ_ROUTING_KEY);
    }

    @Bean
    public Binding smsDlqBinding(
            @Qualifier("smsDeadLetterQueue") Queue queue,
            @Qualifier("deadLetterExchange") TopicExchange Exchange){

        return BindingBuilder.bind(queue)
                .to(Exchange)
                .with(RabbitMQConstants.SMS_DLQ_ROUTING_KEY);
    }

    @Bean
    public Binding pushDlqBinding(
            @Qualifier("pushDeadLetterQueue") Queue queue,
            @Qualifier("deadLetterExchange") TopicExchange Exchange){

        return BindingBuilder.bind(queue)
                .to(Exchange)
                .with(RabbitMQConstants.PUSH_DLQ_ROUTING_KEY);
    }

//    @Bean
//    public Binding emailRetryBinding(
//            @Qualifier("emailRetryQueue") Queue queue,
//            @Qualifier("retryExchange") TopicExchange exchange) {
//
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with(RabbitMQConstants.EMAIL_RETRY_ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding smsRetryBinding(
//            @Qualifier("smsRetryQueue") Queue queue,
//            @Qualifier("retryExchange") TopicExchange exchange) {
//
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with(RabbitMQConstants.SMS_RETRY_ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding pushRetryBinding(
//            @Qualifier("pushRetryQueue") Queue queue,
//            @Qualifier("retryExchange") TopicExchange exchange) {
//
//        return BindingBuilder.bind(queue)
//                .to(exchange)
//                .with(RabbitMQConstants.PUSH_RETRY_ROUTING_KEY);
//    }


    // ADMIN
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }


    // Jackson2JsonMessageConverter
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


//    // LISTENER
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
//            ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory =
//                new SimpleRabbitListenerContainerFactory();
//
//        factory.setConnectionFactory(connectionFactory);
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//
//        return factory;
//    }


}
