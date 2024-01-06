package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoomInputDTO {

    private UUID uuid;
    private Short number;
    private Short width;
    private Short height;
    private Short capacity;
    private String projectionType;
    List<SeatInputDTO> seats;

    public RoomInputDTO() {
    }

    public static Room parseRoom(RoomInputDTO roomInputDTO) {
        return Optional.ofNullable(roomInputDTO).map(dto -> {
            Room room = new Room();
            room.setUuid(dto.getUuid());
            room.setNumber(dto.getNumber());
            room.setWidth(dto.getWidth());
            room.setHeight(dto.getHeight());
            room.setCapacity(dto.getCapacity());
            room.setProjectionType(dto.getProjectionType());
            room.setSeats(dto.getSeats().stream().map(SeatInputDTO::parseSeat).toList());
            return room;
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

    public List<SeatInputDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatInputDTO> seats) {
        this.seats = seats;
    }
}
