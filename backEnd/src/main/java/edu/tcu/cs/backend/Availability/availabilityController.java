package edu.tcu.cs.backend.Availability;

import edu.tcu.cs.backend.System.Result;
import edu.tcu.cs.backend.System.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}")
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
    @PutMapping("/availability")
    public Result updateAvailability(@RequestBody availability updatedAvailability) {
        availability updated = availabilityService.updateAvailability(updatedAvailability);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updated);
    }
}