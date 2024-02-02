package io.github.fgabrielbraga.CineDev.service;

import io.github.fgabrielbraga.CineDev.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;
}
