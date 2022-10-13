package br.com.itau.letscode.agrocinetickets.Ticket.listener;

import br.com.itau.letscode.agrocinetickets.Ticket.config.RabbitMQConfig;
import br.com.itau.letscode.agrocinetickets.Ticket.model.Ticket;
import br.com.itau.letscode.agrocinetickets.Ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsTicketsListener {

    private final TicketService ticketService;

    @RabbitListener(queues = RabbitMQConfig.PAYMENTS_TICKETS_QUEUE)
    public void listener(Ticket ticket) {
        ticketService.updateStatus(ticket.getId(), ticket.getStatus());
    }

}
