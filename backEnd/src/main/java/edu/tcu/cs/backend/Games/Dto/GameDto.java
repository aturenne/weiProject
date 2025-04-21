package edu.tcu.cs.backend.Games.Dto;

import jakarta.validation.constraints.NotEmpty;

public record GameDto(int gameId, int scheduleId,
                              @NotEmpty(message = "Game Date is required") String gameDate,
                              @NotEmpty(message = "Venue is required") String venue,
                              @NotEmpty(message = "Opponent is required") String opponent,
                              Boolean isFinalized) {
}