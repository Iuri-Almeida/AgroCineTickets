package br.com.itau.letscode.agrocinetickets.Payment.listener;

import br.com.itau.letscode.agrocinetickets.Payment.config.RabbitMQConfig;
import br.com.itau.letscode.agrocinetickets.Payment.model.Ticket;
import br.com.itau.letscode.agrocinetickets.Payment.model.enums.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class TicketsPaymentsListener {

    private final RabbitTemplate template;

    @RabbitListener(queues = RabbitMQConfig.TICKETS_PAYMENTS_QUEUE)
    public void listener(Ticket ticket) {
        boolean successfulPayment = new Random().nextInt(100) < 80;
        String exchange;

        if (successfulPayment) {
            ticket.setStatus(TicketStatus.SUCCESS);
            exchange = RabbitMQConfig.PAYMENTS_TICKETS_QUEUE_AND_DIRECT_EXCHANGE;
        } else {
            ticket.setStatus(TicketStatus.ERROR);
            exchange = RabbitMQConfig.PAYMENTS_TICKETS_SESSIONS_FANOUT_EXCHANGE;
        }

        template.convertAndSend(exchange, "", ticket);
    }

}
