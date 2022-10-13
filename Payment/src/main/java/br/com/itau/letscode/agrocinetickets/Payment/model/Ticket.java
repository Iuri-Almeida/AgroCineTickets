package br.com.itau.letscode.agrocinetickets.Payment.model;

import br.com.itau.letscode.agrocinetickets.Payment.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private UUID id;
    private UUID sessionID;
    private Integer line;
    private Integer column;
    private TicketStatus status;

}
