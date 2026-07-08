package com.utkarsh.notification_service.constants;

public class RabbitMQConstants {

    private RabbitMQConstants() {
        // Prevent instantiation
    }

    // QUEUES

    public static final String EMAIL_QUEUE = "email.queue";
    public static final String SMS_QUEUE = "sms.queue";
    public static final String PUSH_QUEUE = "push.queue";

    // EXCHANGE

    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";


    // ROUTING KEYS

    public static final String EMAIL_ROUTING_KEY = "notification.email.#";
    public static final String SMS_ROUTING_KEY = "notification.sms.#";
    public static final String PUSH_ROUTING_KEY = "notification.push.#";

    // DEAD LETTER EXCHANGE

    public static final String DLX_EXCHANGE = "notification.dlx";

    // DEAD LETTER QUEUES

    public static final String EMAIL_DLQ = "email.dlq";
    public static final String SMS_DLQ = "sms.dlq";
    public static final String PUSH_DLQ = "push.dlq";

    // DLQ ROUTING KEYS

    public static final String EMAIL_DLQ_ROUTING_KEY = "notification.email.dlq";
    public static final String SMS_DLQ_ROUTING_KEY = "notification.sms.dlq";
    public static final String PUSH_DLQ_ROUTING_KEY = "notification.push.dlq";

//    // RETRY EXCHANGE
//    public static final String RETRY_EXCHANGE = "notification.retry.exchange";
//
//    // RETRY QUEUES
//    public static final String EMAIL_RETRY_QUEUE = "email.retry.queue";
//    public static final String SMS_RETRY_QUEUE = "sms.retry.queue";
//    public static final String PUSH_RETRY_QUEUE = "push.retry.queue";
//
//    // RETRY ROUTING KEYS
//    public static final String EMAIL_RETRY_ROUTING_KEY = "notification.email.retry";
//    public static final String SMS_RETRY_ROUTING_KEY = "notification.sms.retry";
//    public static final String PUSH_RETRY_ROUTING_KEY = "notification.push.retry";
//
//    // RETRY CONFIGURATION
//    public static final int RETRY_DELAY = 30000;        // 30 seconds
//    public static final int MAX_RETRY_COUNT = 3;


}
