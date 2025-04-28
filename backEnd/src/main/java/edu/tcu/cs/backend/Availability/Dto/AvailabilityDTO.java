package edu.tcu.cs.backend.Availability.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AvailabilityDTO(Integer userId,Integer gameId,
    boolean availability, String comments)

{}