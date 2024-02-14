package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Map;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MapInputDTO {

    private UUID uuid;
    @NotNull(message = "Por favor, forneça uma largura para o mapa.")
    @Min(value = 0, message = "O formato da largura é inválido. Certifique-se de que está correto.")
    private Short width;
    @NotNull(message = "Por favor, forneça uma altura para o mapa.")
    @Min(value = 0, message = "O formato da altura é inválido. Certifique-se de que está correto.")
    private Short height;
    @NotNull(message = "Por favor, forneça uma lista de áreas para o mapa.")
    @NotEmpty(message = "Por favor, preencha a lista de áreas.")
    private List<AreaInputDTO> areas;

    public MapInputDTO() {
    }

    public static Map parseMap(MapInputDTO mapInputDTO) {
        return Optional.ofNullable(mapInputDTO).map(dto -> {
            Map map = new Map();
            map.setUuid(dto.getUuid());
            map.setWidth(dto.getWidth());
            map.setHeight(dto.getHeight());
            map.setAreas(AreaInputDTO.toAreaList(dto.getAreas()));
            return map;
        }).orElse(null);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public List<AreaInputDTO> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaInputDTO> areas) {
        this.areas = areas;
    }
}
