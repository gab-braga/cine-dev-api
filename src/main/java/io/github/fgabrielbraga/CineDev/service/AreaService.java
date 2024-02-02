package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.output.AreaOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Area;
import io.github.fgabrielbraga.CineDev.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public List<AreaOutputDTO> findByRoomId(UUID uuid) {
        List<Area> areas = areaRepository.findByRoomId(uuid);
        return areas.stream().map(AreaOutputDTO::ofArea).toList();
    }
}
