package br.com.itau.letscode.agrocinetickets.Session.listener;

import br.com.itau.letscode.agrocinetickets.Session.config.RabbitMQConfig;
import br.com.itau.letscode.agrocinetickets.Session.model.Ticket;
import br.com.itau.letscode.agrocinetickets.Session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsSessionsListener {

    private final SessionService sessionService;

    @RabbitListener(queues = RabbitMQConfig.PAYMENTS_SESSIONS_QUEUE)
    public void listener(Ticket ticket) {
        sessionService.vacateSeat(ticket.getSessionID(), ticket.getLine(), ticket.getCol());
    }

}
