package br.com.itau.letscode.agrocinetickets.Payment.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PAYMENTS_TICKETS_QUEUE_AND_DIRECT_EXCHANGE = "payments.tickets";
    public static final String TICKETS_PAYMENTS_QUEUE = "tickets.payments";
    public static final String PAYMENTS_SESSIONS_QUEUE = "payments.sessions";
    public static final String PAYMENTS_TICKETS_SESSIONS_FANOUT_EXCHANGE = "payments.tickets_sessions";

    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.port}")
    int port;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Bean
    public Queue ticketsPaymentsQueue() {
        return new Queue(TICKETS_PAYMENTS_QUEUE);
    }

    @Bean
    public DirectExchange paymentsTicketsExchange() {
        return new DirectExchange(PAYMENTS_TICKETS_QUEUE_AND_DIRECT_EXCHANGE);
    }

    @Bean
    public FanoutExchange paymentsTicketsAndRoomsExchange() {
        return new FanoutExchange(PAYMENTS_TICKETS_SESSIONS_FANOUT_EXCHANGE);
    }

    @Bean
    public Queue paymentsTicketsQueue() {
        return new Queue(PAYMENTS_TICKETS_QUEUE_AND_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue paymentsRoomsQueue() {
        return new Queue(PAYMENTS_SESSIONS_QUEUE);
    }

    @Bean
    public Binding paymentsTicketsBinding(
            Queue paymentsTicketsQueue,
            DirectExchange paymentsTicketsExchange
    ) {
        return BindingBuilder
                .bind(paymentsTicketsQueue)
                .to(paymentsTicketsExchange)
                .with("");
    }

    @Bean
    public Binding paymentsTicketsFanoutBinding(
            Queue paymentsTicketsQueue,
            FanoutExchange paymentsTicketsAndRoomsExchange
    ) {
        return BindingBuilder
                .bind(paymentsTicketsQueue)
                .to(paymentsTicketsAndRoomsExchange);
    }

    @Bean
    public Binding paymentsRoomsFanoutBinding(
            Queue paymentsRoomsQueue,
            FanoutExchange paymentsTicketsAndRoomsExchange
    ) {
        return BindingBuilder
                .bind(paymentsRoomsQueue)
                .to(paymentsTicketsAndRoomsExchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(this.messageConverter());
        return template;
    }

}
