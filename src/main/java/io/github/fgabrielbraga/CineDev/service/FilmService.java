package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.FilmDTO;
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

    public Optional<FilmDTO> findById(UUID uuid) {
        Optional<Film> filmOpt = filmRepository.findById(uuid);
        return filmOpt.map(FilmDTO::new);
    }

    public List<FilmDTO> findAll() {
        List<Film> films = filmRepository.findAll();
        return films.stream().map(FilmDTO::new).toList();
    }

    public List<FilmDTO> findByTitleContaining(String title) {
        List<Film> films = filmRepository.findByTitleContainingIgnoreCase(title);
        return films.stream().map(FilmDTO::new).toList();
    }

    public List<FilmDTO> findByGenresContaining(String genres) {
        List<Film> films = filmRepository.findByGenresContainingIgnoreCase(genres);
        return films.stream().map(FilmDTO::new).toList();
    }

    public FilmDTO save(FilmDTO filmDTO) {
        Film film = FilmDTO.convert(filmDTO);
        Film filmSaved = filmRepository.save(film);
        return new FilmDTO(filmSaved);
    }

    public FilmDTO delete(FilmDTO filmDTO) {
        Film film = FilmDTO.convert(filmDTO);
        filmRepository.delete(film);
        return filmDTO;
    }
}
