package edu.tcu.cs.backend.Games.Converter;

import edu.tcu.cs.backend.Games.Dto.GameDto;
import edu.tcu.cs.backend.Games.game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameDtoToGameConverter implements Converter<GameDto, game> {

    @Override
    public game convert(GameDto gameDto) {
        game game = new game();
        game.setGameId(gameDto.gameId());
        game.setScheduleId(gameDto.scheduleId());
        game.setGameDate(gameDto.gameDate());
        game.setVenue(gameDto.venue());
        game.setOpponent(gameDto.opponent());
        game.setIsFinalized(gameDto.isFinalized());

        return game;
    }

}
