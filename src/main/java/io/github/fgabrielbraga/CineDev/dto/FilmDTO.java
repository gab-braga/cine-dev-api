package io.github.fgabrielbraga.CineDev.dto;

import io.github.fgabrielbraga.CineDev.model.Film;

import java.time.LocalDate;
import java.util.UUID;

public class FilmDTO {

    private UUID uuid;
    private String title;
    private String resume;
    private String genres;
    private Float duration;
    private String coverImage;
    private LocalDate publishedIn;

    public FilmDTO() {
    }

    public FilmDTO(Film film) {
        this.uuid = film.getUuid();
        this.title = film.getTitle();
        this.resume = film.getResume();
        this.genres = film.getGenres();
        this.duration = film.getDuration();
        this.coverImage = film.getCoverImage();
        this.publishedIn = film.getPublishedIn();
    }

    public static Film convert(FilmDTO filmDTO) {
        Film film = new Film();
        film.setUuid(filmDTO.getUuid());
        film.setTitle(filmDTO.getTitle());
        film.setResume(filmDTO.getResume());
        film.setGenres(filmDTO.getGenres());
        film.setDuration(filmDTO.getDuration());
        film.setCoverImage(filmDTO.getCoverImage());
        film.setPublishedIn(filmDTO.getPublishedIn());
        return film;
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
