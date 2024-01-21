package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.SeatInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.SeatOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Seat;
import io.github.fgabrielbraga.CineDev.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<SeatOutputDTO> findByRoomId(UUID uuid) {
        List<Seat> seats = seatRepository.findByRoomId(uuid);
        return seats.stream().map(SeatOutputDTO::ofSeat).toList();
    }
}
