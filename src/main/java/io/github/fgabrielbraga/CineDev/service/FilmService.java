package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.FilmInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.FilmOutputDTO;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceNotFoundException;
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

    public FilmOutputDTO findById(UUID uuid) {
        Optional<Film> filmOpt = filmRepository.findById(uuid);
        return filmOpt.map(FilmOutputDTO::ofFilm).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, filme não encontrado. Tente novamente."));
    }

    public List<FilmOutputDTO> findTop1000() {
        List<Film> films = filmRepository.findTop1000();
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public List<FilmOutputDTO> findTop1000ByTitleAndGenres(String title, String genres) {
        List<Film> films = filmRepository.findTop1000ByTitleAndGenres(title, genres);
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public List<FilmOutputDTO> findTop1000ByGenres(String genres) {
        List<Film> films = filmRepository.findTop1000ByGenres(genres);
        return films.stream().map(FilmOutputDTO::ofFilm).toList();
    }

    public FilmOutputDTO save(FilmInputDTO filmDTO) {
        Film film = FilmInputDTO.parseFilm(filmDTO);
        Film filmSaved = filmRepository.save(film);
        return FilmOutputDTO.ofFilm(filmSaved);
    }

    public FilmOutputDTO update(FilmInputDTO filmDTO) {
        Optional<Film> filmOpt = filmRepository
                .findById(filmDTO.getUuid());
        return filmOpt.map(filmFound -> {
            filmFound.setTitle(filmDTO.getTitle());
            filmFound.setResume(filmDTO.getResume());
            filmFound.setGenres(filmDTO.getGenres());
            filmFound.setDuration(filmDTO.getDuration());
            filmFound.setPublishedIn(filmDTO.getPublishedIn());
            filmFound.setCoverImage(filmDTO.getCoverImage());
            Film filmSaved = filmRepository.save(filmFound);
            return FilmOutputDTO.ofFilm(filmSaved);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, filme não encontrado. Tente novamente."));
    }

    public void deleteById(UUID uuid) {
        Optional<Film> filmOpt = filmRepository.findById(uuid);
        filmOpt.orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, filme não encontrado. Tente novamente."));
        filmRepository.deleteById(uuid);
    }
}
