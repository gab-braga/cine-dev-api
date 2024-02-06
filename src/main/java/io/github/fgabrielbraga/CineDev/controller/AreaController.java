package io.github.fgabrielbraga.CineDev.controller;

import io.github.fgabrielbraga.CineDev.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;
}
