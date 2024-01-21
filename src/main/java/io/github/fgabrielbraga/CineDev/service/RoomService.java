package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.input.RoomInputDTO;
import io.github.fgabrielbraga.CineDev.dto.output.RoomOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Room;
import io.github.fgabrielbraga.CineDev.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Optional<RoomOutputDTO> findById(UUID uuid) {
        Optional<Room> roomOpt = roomRepository.findById(uuid);
        return roomOpt.map(RoomOutputDTO::ofRoom);
    }

    public List<RoomOutputDTO> findAll() {
        List<Room> rooms = roomRepository.findAll();
        rooms.stream().forEach(room -> room.getSeats().clear());
        return rooms.stream().map(RoomOutputDTO::ofRoom).toList();
    }

    public List<RoomOutputDTO> findAll(Short number) {
        List<Room> rooms = roomRepository.findAllWithFilter(number);
        rooms.stream().forEach(room -> room.getSeats().clear());
        return rooms.stream().map(RoomOutputDTO::ofRoom).toList();
    }

    public RoomOutputDTO save(RoomInputDTO roomInputDTO) {
        Room room = RoomInputDTO.parseRoom(roomInputDTO);
        room.getSeats().stream().forEach(seat -> seat.setRoom(room));
        Room roomSaved = roomRepository.save(room);
        return RoomOutputDTO.ofRoom(roomSaved);
    }

    public void deleteById(UUID uuid) {
        roomRepository.deleteById(uuid);
    }
}
