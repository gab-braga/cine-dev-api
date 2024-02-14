package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.SessionInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SessionOutputDTO;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceNotFoundException;
import io.github.fgabrielbraga.CineDev.exceptions.ResourceUnavailableException;
import io.github.fgabrielbraga.CineDev.model.Film;
import io.github.fgabrielbraga.CineDev.model.Reservation;
import io.github.fgabrielbraga.CineDev.model.Room;
import io.github.fgabrielbraga.CineDev.model.Session;
import io.github.fgabrielbraga.CineDev.repository.FilmRepository;
import io.github.fgabrielbraga.CineDev.repository.ReservationRepository;
import io.github.fgabrielbraga.CineDev.repository.RoomRepository;
import io.github.fgabrielbraga.CineDev.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ReservationRepository reservationRepository;

    public SessionOutputDTO findById(UUID uuid) {
        Optional<Session> sessionOpt = sessionRepository.findById(uuid);
        return sessionOpt.map(SessionOutputDTO::ofSession).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, sessão não encontrada. Tente novamente."));
    }

    public List<SessionOutputDTO> findAll() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findTop1000ByDateAndNumberAndTitle(LocalDate date, Short number, String title) {
        List<Session> sessions = sessionRepository.findTop1000ByDateAndNumberAndTitle(date, number, title);
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findTop1000RecentByDate(LocalDate date) {
        List<Session> sessions = sessionRepository.findTop1000ByDate(date);
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findTop1000ThisWeek() {
        List<Session> sessions = sessionRepository.findTop1000ThisWeek();
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findTop1000ByRoomId(UUID uuid) {
        List<Session> sessions = sessionRepository.findTop1000ByRoomId(uuid);
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findTop1000ByGenresForClient(String genres) {
        List<Session> sessions = sessionRepository.findTop1000ByGenres(genres);
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public List<SessionOutputDTO> findTop1000ByFilmId(UUID uuid) {
        List<Session> sessions = sessionRepository.findTop1000ByFilmId(uuid);
        return sessions.stream().map(SessionOutputDTO::ofSession).toList();
    }

    public SessionOutputDTO save(SessionInputDTO sessionDTO) {
        Film film = filmRepository
                .findById(sessionDTO.getFilm().getUuid()).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, filme não encontrado. Tente novamente."));
        Room room = roomRepository
                .findById(sessionDTO.getRoom().getUuid()).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, sala não encontrada. Tente novamente."));
        Session session = SessionInputDTO.parseSession(sessionDTO);
        session.setFilm(film);
        session.setRoom(room);
        session.setNumberFreeSeats(room.getCapacity());
        resetIdentifier(session);
        Session sessionSaved = sessionRepository.save(session);
        return SessionOutputDTO.ofSession(sessionSaved);
    }

    public SessionOutputDTO update(SessionInputDTO sessionDTO) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionDTO.getUuid());
        return sessionOpt.map(sessionFound -> {
            Film film = filmRepository
                    .findById(sessionDTO.getFilm().getUuid()).orElseThrow(() ->
                            new ResourceNotFoundException("Desculpe, filme não encontrado. Tente novamente."));
            Room room = roomRepository
                    .findById(sessionDTO.getRoom().getUuid()).orElseThrow(() ->
                            new ResourceNotFoundException("Desculpe, sala não encontrada. Tente novamente."));
            List<Reservation> reservations = this.reservationRepository
                    .findTop1000BySessionId(sessionFound.getUuid());
            if(reservations.isEmpty()) {
                sessionFound.setFilm(film);
                sessionFound.setRoom(room);
                sessionFound.setDate(sessionDTO.getDate());
                sessionFound.setHour(sessionDTO.getHour());
                sessionFound.setTicketPrice(sessionDTO.getTicketPrice());
                sessionFound.setNumberFreeSeats(room.getCapacity());
                Session sessionSaved = sessionRepository.save(sessionFound);
                return SessionOutputDTO.ofSession(sessionSaved);
            }
            throw new ResourceUnavailableException("Desculpe, esta sessão tem reservas. Tente novamente.");
        }).orElseThrow(() ->
                new ResourceNotFoundException("Desculpe, sessão não encontrada. Tente novamente."));
    }

    public void deleteById(UUID uuid) {
        sessionRepository.deleteById(uuid);
    }

    @Transactional
    public void close(UUID uuid) {
        sessionRepository.closeSessionById(uuid);
    }

    @Transactional
    public void open(UUID uuid) {
        sessionRepository.openSessionById(uuid);
    }

    private void resetIdentifier(Session session) {
        session.setUuid(null);
        session.getTickets().forEach(ticket -> ticket.setUuid(null));
    }
}
