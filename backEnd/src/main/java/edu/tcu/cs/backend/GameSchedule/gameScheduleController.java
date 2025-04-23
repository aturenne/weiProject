package edu.tcu.cs.backend.GameSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class gameScheduleController {

    private final gameScheduleService gameScheduleService;

    @Autowired
    public gameScheduleController(gameScheduleService gameScheduleService) {
        this.gameScheduleService = gameScheduleService;
    }

}