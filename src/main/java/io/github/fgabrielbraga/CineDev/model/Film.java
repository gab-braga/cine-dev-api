package io.github.fgabrielbraga.CineDev.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false, unique = true, length = 120)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String resume;
    @Column(nullable = false)
    private String genres;
    @Column(nullable = false)
    private Float duration;
    @Column(nullable = false, name = "cover_image", columnDefinition = "MEDIUMTEXT")
    private String coverImage;
    @Column(nullable = false, name = "published_in")
    private LocalDate publishedIn;

    @PrePersist
    protected void onCreate() {
        this.uuid = null;
    }

    public Film() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public LocalDate getPublishedIn() {
        return publishedIn;
    }

    public void setPublishedIn(LocalDate publishedIn) {
        this.publishedIn = publishedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(getUuid(), film.getUuid()) &&
                Objects.equals(getTitle(), film.getTitle()) &&
                Objects.equals(getResume(), film.getResume()) &&
                Objects.equals(getGenres(), film.getGenres()) &&
                Objects.equals(getDuration(), film.getDuration()) &&
                Objects.equals(getCoverImage(), film.getCoverImage()) &&
                Objects.equals(getPublishedIn(), film.getPublishedIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(),
                getTitle(),
                getResume(),
                getGenres(),
                getDuration(),
                getCoverImage(),
                getPublishedIn());
    }
}
