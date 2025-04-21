package edu.tcu.cs.backend.Games.Converter;

import edu.tcu.cs.backend.Games.Dto.GameDto;
import edu.tcu.cs.backend.Games.game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameToGameDtoConverter implements Converter<game, GameDto> {

    @Override
    public GameDto convert(game source) {
        final GameDto gameDto = new GameDto(
                source.getGameId(),
                source.getScheduleId(),
                source.getGameDate(),
                source.getVenue(),
                source.getOpponent(),
                source.getIsFinalized()
        );
        return gameDto;
    }
}
