package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Room;

import java.util.Optional;
import java.util.UUID;

public class RoomOutputDTO {

    private UUID uuid;
    private Short number;
    private Short capacity;
    private String projectionType;
    private MapOutputDTO map;

    public RoomOutputDTO() {
    }

    public static RoomOutputDTO ofRoom(Room roomObj) {
        return Optional.ofNullable(roomObj).map(room -> {
            RoomOutputDTO roomOutputDTO = new RoomOutputDTO();
            roomOutputDTO.uuid = room.getUuid();
            roomOutputDTO.number = room.getNumber();
            roomOutputDTO.capacity = room.getCapacity();
            roomOutputDTO.projectionType = room.getProjectionType();
            roomOutputDTO.map = MapOutputDTO.ofMap(room.getMap());
            return roomOutputDTO;
        }).orElse(null);
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

    public MapOutputDTO getMap() {
        return map;
    }

    public void setMap(MapOutputDTO map) {
        this.map = map;
    }
}
