package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.SessionInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Film;
import io.github.fgabrielbraga.CineDev.model.Room;
import io.github.fgabrielbraga.CineDev.model.Session;
import io.github.fgabrielbraga.CineDev.repository.FilmRepository;
import io.github.fgabrielbraga.CineDev.repository.RoomRepository;
import io.github.fgabrielbraga.CineDev.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private RoomRepository roomRepository;

    public Optional<SessionOutputDTO> findById(UUID uuid) {
        Optional<Session> sessionOpt = sessionRepository.findById(uuid);
        return sessionOpt.map(SessionOutputDTO::ofSession);
    }

    public List<SessionOutputDTO> findAll() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findAll(LocalDate date, Short number, String title) {
        List<Session> sessions = sessionRepository.findAllWithFilter(date, number, title);
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public SessionOutputDTO save(SessionInputDTO sessionInputDTO) {
        Session session = SessionInputDTO.parseSession(sessionInputDTO);
        Session sessionSaved = sessionRepository.save(session);
        return SessionOutputDTO.ofSession(sessionSaved);
    }

    public SessionOutputDTO save(UUID filmId, UUID roomId, SessionInputDTO sessionInputDTO) {
        Film film = filmRepository.findById(filmId).orElseThrow();
        Room room = roomRepository.findById(roomId).orElseThrow();
        Session session = SessionInputDTO.parseSession(sessionInputDTO);
        session.setFilm(film);
        session.setRoom(room);
        session.setNumberFreeSeats(room.getCapacity());
        Session sessionSaved = sessionRepository.save(session);
        return SessionOutputDTO.ofSession(sessionSaved);
    }

    public void deleteById(UUID uuid) {
        sessionRepository.deleteById(uuid);
    }
}
