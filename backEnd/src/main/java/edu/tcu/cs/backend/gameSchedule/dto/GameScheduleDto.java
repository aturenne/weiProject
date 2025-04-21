package edu.tcu.cs.backend.gameSchedule.dto;

import jakarta.validation.constraints.NotEmpty;



public record GameScheduleDto(int scheduleId,
                              @NotEmpty (message = "Sports is required") String sport,
                              @NotEmpty (message = "Season is required") String season) {
}