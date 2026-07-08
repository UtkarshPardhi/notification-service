package com.utkarsh.notification_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
@RequiredArgsConstructor
public class RabbitListenerConfig {

    private final ConnectionFactory connectionFactory;
    private final RetryOperationsInterceptor retryOperationsInterceptor;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);

        factory.setAdviceChain(retryOperationsInterceptor);

        factory.setDefaultRequeueRejected(false);

        return factory;
    }
}
