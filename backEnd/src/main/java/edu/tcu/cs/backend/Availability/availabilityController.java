package edu.tcu.cs.backend.Availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/availability")
public class availabilityController {

    private final availabilityService availabilityService;

    @Autowired
    public availabilityController(availabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/{userId}/schedule/{scheduleId}")
    public availability getAvailability(@PathVariable int userId, @PathVariable int scheduleId) {
        return availabilityService.getAvailabilityByUserIdAndScheduleId(userId, scheduleId);
    }
}