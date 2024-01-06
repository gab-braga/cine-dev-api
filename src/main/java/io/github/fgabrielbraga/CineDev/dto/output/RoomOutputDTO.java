package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Room;

import java.util.Optional;
import java.util.UUID;

public class RoomOutputDTO {

    private UUID uuid;
    private Short number;
    private Short width;
    private Short height;
    private Short capacity;
    private String projectionType;

    public RoomOutputDTO() {
    }

    public static RoomOutputDTO ofRoom(Room roomObj) {
        return Optional.ofNullable(roomObj).map(room -> {
            RoomOutputDTO roomOutputDTO = new RoomOutputDTO();
            roomOutputDTO.uuid = room.getUuid();
            roomOutputDTO.number = room.getNumber();
            roomOutputDTO.width = room.getWidth();
            roomOutputDTO.height = room.getHeight();
            roomOutputDTO.capacity = room.getCapacity();
            roomOutputDTO.projectionType = room.getProjectionType();
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
}
