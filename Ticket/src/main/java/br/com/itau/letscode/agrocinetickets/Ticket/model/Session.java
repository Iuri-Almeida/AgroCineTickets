package br.com.itau.letscode.agrocinetickets.Ticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private UUID id;
    private UUID movieID;
    private UUID roomId;
    private LocalTime startTime;
    private LocalTime endTime;

}
