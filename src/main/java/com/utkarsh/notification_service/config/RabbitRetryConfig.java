package com.utkarsh.notification_service.config;

import com.utkarsh.notification_service.constants.RabbitMQConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
@RequiredArgsConstructor
public class RabbitRetryConfig {

    private final RabbitTemplate rabbitTemplate;
    private final ConnectionFactory connectionFactory;
    private final Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    // EMAIL
    @Bean
    public MessageRecoverer emailMessageRecoverer() {

        return new RepublishMessageRecoverer(
                rabbitTemplate,
                RabbitMQConstants.DLX_EXCHANGE,
                RabbitMQConstants.EMAIL_DLQ_ROUTING_KEY
        );
    }

    @Bean
    public RetryOperationsInterceptor emailRetryInterceptor(
            @Qualifier("emailMessageRecoverer")
            MessageRecoverer emailMessageRecoverer) {

        return RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .backOffOptions(1000, 2.0, 10000)
                .recoverer(emailMessageRecoverer)
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory emailRetryContainerFactory(
            @Qualifier("emailRetryInterceptor")
            RetryOperationsInterceptor emailRetryInterceptor) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);

        factory.setAdviceChain(emailRetryInterceptor);

        factory.setDefaultRequeueRejected(false);

        return factory;
    }

    // SMS
    @Bean
    public MessageRecoverer smsMessageRecoverer() {

        return new RepublishMessageRecoverer(
                rabbitTemplate,
                RabbitMQConstants.DLX_EXCHANGE,
                RabbitMQConstants.SMS_DLQ_ROUTING_KEY
        );
    }

    @Bean
    public RetryOperationsInterceptor smsRetryInterceptor(
            @Qualifier("smsMessageRecoverer")
            MessageRecoverer smsMessageRecoverer) {

        return RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .backOffOptions(1000, 2.0, 10000)
                .recoverer(smsMessageRecoverer)
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory smsRetryContainerFactory(
            @Qualifier("smsRetryInterceptor")
            RetryOperationsInterceptor smsRetryInterceptor) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);

        factory.setAdviceChain(smsRetryInterceptor);

        factory.setDefaultRequeueRejected(false);

        return factory;
    }

    // PUSH
    @Bean
    public MessageRecoverer pushMessageRecoverer() {

        return new RepublishMessageRecoverer(
                rabbitTemplate,
                RabbitMQConstants.DLX_EXCHANGE,
                RabbitMQConstants.PUSH_DLQ_ROUTING_KEY
        );
    }

    @Bean
    public RetryOperationsInterceptor pushRetryInterceptor(
            @Qualifier("pushMessageRecoverer")
            MessageRecoverer pushMessageRecoverer) {

        return RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .backOffOptions(1000, 2.0, 10000)
                .recoverer(pushMessageRecoverer)
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory pushRetryContainerFactory(
            @Qualifier("pushRetryInterceptor")
            RetryOperationsInterceptor pushRetryInterceptor) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);

        factory.setAdviceChain(pushRetryInterceptor);

        factory.setDefaultRequeueRejected(false);

        return factory;
    }

}
