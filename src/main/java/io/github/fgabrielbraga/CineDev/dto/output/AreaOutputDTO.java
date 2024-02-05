package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.enums.AreaType;
import io.github.fgabrielbraga.CineDev.model.Area;

import java.util.Optional;
import java.util.UUID;

public class AreaOutputDTO {

    private UUID uuid;
    private Short number;
    private Short indexInX;
    private Short indexInY;
    private AreaType areaType;

    public AreaOutputDTO() {
    }

    public static AreaOutputDTO ofArea(Area areaObj) {
        return Optional.ofNullable(areaObj).map(area -> {
            AreaOutputDTO areaOutputDTO = new AreaOutputDTO();
            areaOutputDTO.uuid = area.getUuid();
            areaOutputDTO.number = area.getNumber();
            areaOutputDTO.indexInX = area.getIndexInX();
            areaOutputDTO.indexInY = area.getIndexInY();
            areaOutputDTO.areaType = area.getAreaType();
            return areaOutputDTO;
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
