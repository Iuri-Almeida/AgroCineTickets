package br.com.itau.letscode.agrocinetickets.Ticket.config;

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

    public static final String TICKETS_PAYMENTS_QUEUE = "tickets.payments";
    public static final String PAYMENTS_TICKETS_QUEUE = "payments.tickets";
    public static final String TICKETS_PAYMENTS_DIRECT_EXCHANGE = "ticketsPaymentsDirect";

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
    public Queue paymentsTicketsQueue() {
        return new Queue(PAYMENTS_TICKETS_QUEUE);
    }

    @Bean
    public DirectExchange ticketsPaymentsDirectExchange() {
        return new DirectExchange(TICKETS_PAYMENTS_DIRECT_EXCHANGE);
    }

    @Bean
    public Binding ticketsPaymentsDirectBinding(
            Queue ticketsPaymentsQueue,
            DirectExchange ticketsPaymentsDirectExchange
    ) {
        return BindingBuilder
                .bind(ticketsPaymentsQueue)
                .to(ticketsPaymentsDirectExchange)
                .with("");
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
