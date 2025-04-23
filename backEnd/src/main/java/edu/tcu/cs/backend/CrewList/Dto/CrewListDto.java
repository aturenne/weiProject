package edu.tcu.cs.backend.CrewList.Dto;

import edu.tcu.cs.backend.CrewedUser.Dto.CrewedUserDto;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CrewListDto(
        Integer gameId,
        @NotEmpty(message = "Game Start is required") String gameStart,
        @NotEmpty(message = "Game Date is required") String gameDate,
        @NotEmpty(message = "Venue is required") String venue,
        @NotEmpty(message = "Opponent is required") String opponent
) {
}

