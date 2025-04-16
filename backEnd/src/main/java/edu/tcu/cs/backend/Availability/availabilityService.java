package edu.tcu.cs.backend.Availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class availabilityService {

    private final availabilityRepository availabilityRepository;

    @Autowired
    public availabilityService(availabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public availability getAvailabilityByUserIdAndScheduleId(int userId, int scheduleId) {
        return availabilityRepository.findByUserIdAndGameId(userId, scheduleId)
                .orElseThrow(() -> new availabilityNotFoundException(userId, scheduleId));
    }
}