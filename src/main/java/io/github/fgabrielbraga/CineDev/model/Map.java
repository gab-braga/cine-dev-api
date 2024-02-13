package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "maps")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false)
    private Short width;
    @Column(nullable = false)
    private Short height;
    @OneToMany(mappedBy = "map", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Area> areas = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.uuid = null;
    }

    public Map() {
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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Objects.equals(getUuid(), map.getUuid()) &&
                Objects.equals(getWidth(), map.getWidth()) &&
                Objects.equals(getHeight(), map.getHeight()) &&
                Objects.equals(getAreas(), map.getAreas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getWidth(),
                getHeight(),
                getAreas());
    }
}
