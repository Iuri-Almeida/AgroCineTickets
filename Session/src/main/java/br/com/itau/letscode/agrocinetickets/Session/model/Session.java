package br.com.itau.letscode.agrocinetickets.Session.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tb_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private UUID movieID;
    private UUID roomId;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean[][] seats;

    public Session(UUID id, UUID movieID, UUID roomId, LocalTime startTime, LocalTime endTime, int lines, int columns) {
        this.id = id;
        this.movieID = movieID;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = new boolean[lines][columns];
    }

    public void occupySeat(int line, int column) {
        this.seats[line][column] = true;
    }

    public void vacateSeat(int line, int column) {
        this.seats[line][column] = false;
    }

}
