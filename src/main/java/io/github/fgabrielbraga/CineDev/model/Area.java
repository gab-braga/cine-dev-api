package io.github.fgabrielbraga.CineDev.model;

import io.github.fgabrielbraga.CineDev.enums.AreaType;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "area_number", nullable = false)
    private Short number;
    @Column(name = "index_in_x", nullable = false)
    private Short indexInX;
    @Column(name = "index_in_y", nullable = false)
    private Short indexInY;
    @Enumerated(EnumType.STRING)
    @Column(name = "area_type", nullable = false)
    private AreaType areaType;
    @ManyToOne
    @JoinColumn(name = "map_uuid", nullable = false)
    private Map map;

    public Area() {
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(getUuid(), area.getUuid()) &&
                Objects.equals(getNumber(), area.getNumber()) &&
                Objects.equals(getIndexInX(), area.getIndexInX()) &&
                Objects.equals(getIndexInY(), area.getIndexInY()) &&
                Objects.equals(getMap(), area.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getNumber(),
                getIndexInX(),
                getIndexInY(),
                getMap());
    }
}
