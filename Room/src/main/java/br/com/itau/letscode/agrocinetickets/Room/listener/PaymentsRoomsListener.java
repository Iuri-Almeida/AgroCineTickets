package br.com.itau.letscode.agrocinetickets.Room.listener;

import br.com.itau.letscode.agrocinetickets.Room.config.RabbitMQConfig;
import br.com.itau.letscode.agrocinetickets.Room.model.Session;
import br.com.itau.letscode.agrocinetickets.Room.model.Ticket;
import br.com.itau.letscode.agrocinetickets.Room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsRoomsListener {

    private final RoomService roomService;

    @RabbitListener(queues = RabbitMQConfig.PAYMENTS_ROOMS_QUEUE)
    public void listener(Ticket ticket) {
        Session session = roomService.findSessionById(ticket.getSessionID());
        roomService.vacateSeat(session.getRoomId(), ticket.getLine(), ticket.getColumn());
    }

}
