package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "room_number", nullable = false, unique = true)
    private Short number;
    @Column(nullable = false)
    private Short capacity;
    @Column(name = "projection_type", nullable = false, length = 50)
    private String projectionType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "map_uuid", nullable = false)
    private Map map;

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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getUuid(), room.getUuid()) &&
                Objects.equals(getNumber(), room.getNumber()) &&
                Objects.equals(getCapacity(), room.getCapacity()) &&
                Objects.equals(getProjectionType(), room.getProjectionType()) &&
                Objects.equals(getMap(), room.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getNumber(),
                getCapacity(),
                getProjectionType(),
                getMap());
    }
}
