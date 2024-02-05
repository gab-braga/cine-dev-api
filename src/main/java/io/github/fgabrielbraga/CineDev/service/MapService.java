package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.dto.output.MapOutputDTO;
import io.github.fgabrielbraga.CineDev.model.Map;
import io.github.fgabrielbraga.CineDev.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    public Optional<MapOutputDTO> findMapByRoomId(UUID uuid) {
        Optional<Map> mapOpt = mapRepository.findMapByRoomId(uuid);
        return mapOpt.map(map -> {
            return MapOutputDTO.ofMap(map);
        });
    }
}
