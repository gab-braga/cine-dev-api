package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.model.Map;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MapInputDTO {

    private UUID uuid;
    private Short width;
    private Short height;
    List<AreaInputDTO> areas;

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
