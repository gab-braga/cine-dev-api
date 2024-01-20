package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "number_seat", nullable = false)
    private Short number;
    @Column(name = "position_in_x", nullable = false)
    private Short positionInX;
    @Column(name = "position_in_y", nullable = false)
    private Short positionInY;
    @Column(name = "empty_space", nullable = false)
    private Boolean empty;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Seat() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Short getPositionInX() {
        return positionInX;
    }

    public void setPositionInX(Short positionInX) {
        this.positionInX = positionInX;
    }

    public Short getPositionInY() {
        return positionInY;
    }

    public void setPositionInY(Short positionInY) {
        this.positionInY = positionInY;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(getUuid(), seat.getUuid()) &&
                Objects.equals(getNumber(), seat.getNumber()) &&
                Objects.equals(getPositionInX(), seat.getPositionInX()) &&
                Objects.equals(getPositionInY(), seat.getPositionInY()) &&
                Objects.equals(getEmpty(), seat.getEmpty()) &&
                Objects.equals(getRoom(), seat.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getNumber(),
                getPositionInX(),
                getPositionInY(),
                getEmpty(),
                getRoom());
    }
}
