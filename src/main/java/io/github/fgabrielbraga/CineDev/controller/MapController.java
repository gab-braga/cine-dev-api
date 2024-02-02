package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maps")
public class MapController {

    @Autowired
    private MapService mapService;
}
