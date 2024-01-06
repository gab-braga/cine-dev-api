package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Seat;

import java.util.Optional;
import java.util.UUID;

public class SeatOutputDTO {

    private UUID uuid;
    private Short number;
    private Short positionInX;
    private Short positionInY;
    private UUID uuidRoom;

    public SeatOutputDTO() {
    }

    public static SeatOutputDTO ofSeat(Seat seatObj) {
        return Optional.ofNullable(seatObj).map(seat -> {
            SeatOutputDTO seatInputDTO = new SeatOutputDTO();
            seatInputDTO.uuid = seat.getUuid();
            seatInputDTO.number = seat.getNumber();
            seatInputDTO.positionInX = seat.getPositionInX();
            seatInputDTO.positionInY = seat.getPositionInY();
            seatInputDTO.uuidRoom = seat.getRoom().getUuid();
            return seatInputDTO;
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

    public UUID getUuidRoom() {
        return uuidRoom;
    }

    public void setUuidRoom(UUID uuidRoom) {
        this.uuidRoom = uuidRoom;
    }
}
