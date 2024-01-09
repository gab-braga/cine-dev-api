package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.ReservationInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.ReservationOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Reservation;
import io.github.fgabrielbraga.CineDev.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Optional<ReservationOutputDTO> findById(UUID uuid) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(uuid);
        return reservationOpt.map(ReservationOutputDTO::ofReservation);
    }

    public List<ReservationOutputDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationOutputDTO::ofReservation).toList();
    }

    public ReservationOutputDTO save(ReservationInputDTO reservationInputDTO) {
        Reservation reservation = ReservationInputDTO.parseReservation(reservationInputDTO);
        Reservation reservationSaved = reservationRepository.save(reservation);
        return ReservationOutputDTO.ofReservation(reservationSaved);
    }

    public void deleteById(UUID uuid) {
        reservationRepository.deleteById(uuid);
    }
}
