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

    public Optional<SeatOutputDTO> findById(UUID uuid) {
        Optional<Seat> seatOpt = seatRepository.findById(uuid);
        return seatOpt.map(SeatOutputDTO::ofSeat);
    }

    public List<SeatOutputDTO> findAll() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream().map(SeatOutputDTO::ofSeat).toList();
    }

    public SeatOutputDTO save(SeatInputDTO seatInputDTO) {
        Seat seat = SeatInputDTO.parseSeat(seatInputDTO);
        Seat seatSaved = seatRepository.save(seat);
        return SeatOutputDTO.ofSeat(seatSaved);
    }

    public void deleteById(UUID uuid) {
        seatRepository.deleteById(uuid);
    }
}
