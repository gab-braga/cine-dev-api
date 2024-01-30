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

    public List<FilmOutputDTO> findAll(String title, String genres) {
        List<Film> films = filmRepository.findAllWithFilter(title, genres);
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public List<FilmOutputDTO> findForClient() {
        List<Film> films = filmRepository.findForClient();
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public Optional<FilmOutputDTO> findByIdForClient(UUID uuid) {
        Optional<Film> filmOpt = filmRepository.findById(uuid);
        return filmOpt.map(FilmOutputDTO::ofFilm);
    }

    public FilmOutputDTO save(FilmInputDTO filmDTO) {
        Film film = FilmInputDTO.parseFilm(filmDTO);
        Film filmSaved = filmRepository.save(film);
        return FilmOutputDTO.ofFilm(filmSaved);
    }

    public FilmOutputDTO update(FilmInputDTO filmDTO) {
        Optional<Film> filmOpt = filmRepository.findById(filmDTO.getUuid());
        return filmOpt.map(filmFound -> {
            filmFound.setTitle(filmDTO.getTitle());
            filmFound.setResume(filmDTO.getResume());
            filmFound.setGenres(filmDTO.getGenres());
            filmFound.setDuration(filmDTO.getDuration());
            filmFound.setPublishedIn(filmDTO.getPublishedIn());
            filmFound.setCoverImage(filmDTO.getCoverImage());
            Film filmSaved = filmRepository.save(filmFound);
            return FilmOutputDTO.ofFilm(filmSaved);
        }).orElseThrow();
    }

    public void deleteById(UUID uuid) {
        filmRepository.deleteById(uuid);
    }
}
