package io.github.fgabrielbraga.CineDev.dto.output;

import io.github.fgabrielbraga.CineDev.model.Film;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class FilmOutputDTO {

    private UUID uuid;
    private String title;
    private String resume;
    private String genres;
    private Float duration;
    private String coverImage;
    private LocalDate publishedIn;

    public FilmOutputDTO() {
    }

    public static FilmOutputDTO ofFilm(Film filmObj) {
        return Optional.ofNullable(filmObj).map(film -> {
            FilmOutputDTO filmOutputDTO = new FilmOutputDTO();
            filmOutputDTO.uuid = film.getUuid();
            filmOutputDTO.title = film.getTitle();
            filmOutputDTO.resume = film.getResume();
            filmOutputDTO.genres = film.getGenres();
            filmOutputDTO.duration = film.getDuration();
            filmOutputDTO.coverImage = film.getCoverImage();
            filmOutputDTO.publishedIn = film.getPublishedIn();
            return filmOutputDTO;
        }).orElse(null);
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
}
