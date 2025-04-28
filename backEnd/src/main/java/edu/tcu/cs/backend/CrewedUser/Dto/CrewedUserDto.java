package edu.tcu.cs.backend.CrewedUser.Dto;

import edu.tcu.cs.backend.CrewList.Dto.CrewListDto;
import edu.tcu.cs.backend.CrewList.crewList;
import jakarta.validation.constraints.NotEmpty;

public record CrewedUserDto(
        Integer userId,
        String position,
        String fullName,
        String reportTime,
        String reportLocation

) {}

