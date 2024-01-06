package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.FilmInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Film;
import io.github.fgabrielbraga.CineDev.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Optional<FilmOutputDTO> findById(UUID uuid) {
        Optional<Film> filmOpt = filmRepository.findById(uuid);
        return filmOpt.map(FilmOutputDTO::ofFilm);
    }

    public List<FilmOutputDTO> findAll() {
        List<Film> films = filmRepository.findAll();
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public List<FilmOutputDTO> findByTitleContaining(String title) {
        List<Film> films = filmRepository.findByTitleContainingIgnoreCase(title);
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public List<FilmOutputDTO> findByGenresContaining(String genres) {
        List<Film> films = filmRepository.findByGenresContainingIgnoreCase(genres);
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public FilmOutputDTO save(FilmInputDTO filmInputDTO) {
        Film film = FilmInputDTO.parseFilm(filmInputDTO);
        Film filmSaved = filmRepository.save(film);
        return FilmOutputDTO.ofFilm(filmSaved);
    }

    public void deleteById(UUID uuid) {
        filmRepository.deleteById(uuid);
    }
}
