package io.github.fgabrielbraga.CineDev.dto.input;

import io.github.fgabrielbraga.CineDev.enums.AreaType;
import io.github.fgabrielbraga.CineDev.model.Area;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AreaInputDTO {

    private UUID uuid;
    private Short number;
    private Short indexInX;
    private Short indexInY;
    private AreaType areaType;

    public AreaInputDTO() {
    }

    public static Area parseArea(AreaInputDTO areaInputDTO) {
        return Optional.ofNullable(areaInputDTO).map(dto -> {
            Area area = new Area();
            area.setUuid(dto.getUuid());
            area.setNumber(dto.getNumber());
            area.setIndexInX(dto.getIndexInX());
            area.setIndexInY(dto.getIndexInY());
            area.setAreaType(dto.getAreaType());
            return area;
        }).orElse(null);
    }

    public static List<Area> toAreaList(List<AreaInputDTO> areas) {
        return Optional.ofNullable(areas).map(list -> {
            return list.stream().map(area -> parseArea(area)).toList();
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

    public Short getIndexInX() {
        return indexInX;
    }

    public void setIndexInX(Short indexInX) {
        this.indexInX = indexInX;
    }

    public Short getIndexInY() {
        return indexInY;
    }

    public void setIndexInY(Short indexInY) {
        this.indexInY = indexInY;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    public void setAreaType(AreaType areaType) {
        this.areaType = areaType;
    }
}
