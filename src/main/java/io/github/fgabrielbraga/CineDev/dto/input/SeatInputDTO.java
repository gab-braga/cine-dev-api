package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Seat;

import java.util.Optional;
import java.util.UUID;

public class SeatInputDTO {

    private UUID uuid;
    private Short number;
    private Short positionInX;
    private Short positionInY;
    private RoomInputDTO room;

    public SeatInputDTO() {
    }

    public static Seat parseSeat(SeatInputDTO seatInputDTO) {
        return Optional.ofNullable(seatInputDTO).map(dto -> {
            Seat seat = new Seat();
            seat.setUuid(dto.getUuid());
            seat.setNumber(dto.getNumber());
            seat.setPositionInX(dto.getPositionInX());
            seat.setPositionInY(dto.getPositionInY());
            seat.setRoom(RoomInputDTO.parseRoom(dto.getRoom()));
            return seat;
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

    public RoomInputDTO getRoom() {
        return room;
    }

    public void setRoom(RoomInputDTO room) {
        this.room = room;
    }
}