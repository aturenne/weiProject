package edu.tcu.cs.backend.gameSchedule.converter;

import edu.tcu.cs.backend.gameSchedule.dto.GameScheduleDto;
import edu.tcu.cs.backend.gameSchedule.gameSchedule;
import  org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GsToGsDtoConverter implements Converter<gameSchedule, GameScheduleDto> {

    @Override
    public GameScheduleDto convert(gameSchedule source) {
        final GameScheduleDto gameScheduleDto = new GameScheduleDto(
                source.getId(),
                source.getSeason(),
                source.getSport()
        );
    return gameScheduleDto;
    }
}
