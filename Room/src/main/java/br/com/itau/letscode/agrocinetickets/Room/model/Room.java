package br.com.itau.letscode.agrocinetickets.Room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tb_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private Integer columns;
    private Integer lines;
    private boolean[][] seats;

    public void occupySeat(int line, int column) {
        this.seats[line][column] = true;
    }

    public void vacateSeat(int line, int column) {
        this.seats[line][column] = false;
    }

}
