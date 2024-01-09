package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "number_room", nullable = false, unique = true)
    private Short number;
    @Column(nullable = false)
    private Short width;
    @Column(nullable = false)
    private Short height;
    @Column(nullable = false)
    private Short capacity;
    @Column(name = "projection_type", nullable = false, length = 50)
    private String projectionType;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<Seat> seats;

    public Room() {
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

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getUuid(), room.getUuid()) &&
                Objects.equals(getNumber(), room.getNumber()) &&
                Objects.equals(getWidth(), room.getWidth()) &&
                Objects.equals(getHeight(), room.getHeight()) &&
                Objects.equals(getCapacity(), room.getCapacity()) &&
                Objects.equals(getProjectionType(), room.getProjectionType()) &&
                Objects.equals(getSeats(), room.getSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getNumber(),
                getWidth(),
                getHeight(),
                getCapacity(),
                getProjectionType(),
                getSeats());
    }
}
