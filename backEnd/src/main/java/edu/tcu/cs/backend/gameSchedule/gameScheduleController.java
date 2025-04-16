package edu.tcu.cs.backend.gameSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameSchedule")
public class gameScheduleController {

    private final gameScheduleService gameScheduleService;

    @Autowired
    public gameScheduleController(gameScheduleService gameScheduleService) {
        this.gameScheduleService = gameScheduleService;
    }

    @GetMapping("/games")
    public List<gameSchedule> getAllGameSchedules() {
        return gameScheduleService.getAllGameSchedules();
    }
}