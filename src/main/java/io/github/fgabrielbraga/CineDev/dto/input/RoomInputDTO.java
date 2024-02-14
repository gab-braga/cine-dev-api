package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Room;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Optional;
import java.util.UUID;

public class RoomInputDTO {

    private UUID uuid;
    @NotNull(message = "Por favor, forneça um número para a sala.")
    @Min(value = 0, message = "O formato do número da sala é inválido. Certifique-se de que está correto.")
    private Short number;
    @Min(value = 0, message = "O formato da capacidade é inválido. Certifique-se de que está correto.")
    private Short capacity;
    @NotBlank(message = "Por favor, forneça uma projeção para a sala.")
    @Size(max = 50, message = "O número de caracteres da projeção excede o limite.")
    private String projectionType;
    @NotNull(message = "Por favor, forneça um mapa de assentos para a sala.")
    private MapInputDTO map;

    public RoomInputDTO() {
    }

    public static Room parseRoom(RoomInputDTO roomInputDTO) {
        return Optional.ofNullable(roomInputDTO).map(dto -> {
            Room room = new Room();
            room.setUuid(dto.getUuid());
            room.setNumber(dto.getNumber());
            room.setCapacity(dto.getCapacity());
            room.setProjectionType(dto.getProjectionType());
            room.setMap(MapInputDTO.parseMap(dto.getMap()));
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

    public MapInputDTO getMap() {
        return map;
    }

    public void setMap(MapInputDTO map) {
        this.map = map;
    }
}
