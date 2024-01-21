package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Seat;

import java.util.Optional;
import java.util.UUID;

public class SeatOutputDTO {

    private UUID uuid;
    private Short number;
    private Short position;
    private Short positionInX;
    private Short positionInY;
    private Boolean empty;
    private UUID uuidRoom;

    public SeatOutputDTO() {
    }

    public static SeatOutputDTO ofSeat(Seat seatObj) {
        return Optional.ofNullable(seatObj).map(seat -> {
            SeatOutputDTO seatOutputDTO = new SeatOutputDTO();
            seatOutputDTO.uuid = seat.getUuid();
            seatOutputDTO.number = seat.getNumber();
            seatOutputDTO.position = seat.getPosition();
            seatOutputDTO.positionInX = seat.getPositionInX();
            seatOutputDTO.positionInY = seat.getPositionInY();
            seatOutputDTO.empty = seat.getEmpty();
            seatOutputDTO.uuidRoom = seat.getRoom().getUuid();
            return seatOutputDTO;
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

    public Short getPosition() {
        return position;
    }

    public void setPosition(Short position) {
        this.position = position;
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

    public UUID getUuidRoom() {
        return uuidRoom;
    }

    public void setUuidRoom(UUID uuidRoom) {
        this.uuidRoom = uuidRoom;
    }
}
