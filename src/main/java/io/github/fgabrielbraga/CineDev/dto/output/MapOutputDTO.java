package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Map;

import java.util.Optional;
import java.util.UUID;

public class MapOutputDTO {

    private UUID uuid;
    private Short width;
    private Short height;

    public MapOutputDTO() {
    }

    public static MapOutputDTO ofMap(Map mapObj) {
        return Optional.ofNullable(mapObj).map(map -> {
            MapOutputDTO mapOutputDTO = new MapOutputDTO();
            mapOutputDTO.uuid = map.getUuid();
            mapOutputDTO.width = map.getWidth();
            mapOutputDTO.height = map.getHeight();
            return mapOutputDTO;
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
}
