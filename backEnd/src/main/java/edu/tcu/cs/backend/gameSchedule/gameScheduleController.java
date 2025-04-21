package edu.tcu.cs.backend.gameSchedule;

import edu.tcu.cs.backend.Games.Dto.GameDto;
import edu.tcu.cs.backend.Games.game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class gameScheduleController {

    private final gameScheduleService gameScheduleService;

    @Autowired
    public gameScheduleController(gameScheduleService gameScheduleService) {
        this.gameScheduleService = gameScheduleService;
    }

}