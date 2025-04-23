package edu.tcu.cs.backend.GameSchedule.converter;

import edu.tcu.cs.backend.GameSchedule.dto.GameScheduleDto;
import edu.tcu.cs.backend.GameSchedule.gameSchedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GsDtoToGsConverter implements Converter<GameScheduleDto, gameSchedule> {

    @Override
    public gameSchedule convert(GameScheduleDto gameScheduleDto) {
        gameSchedule gameSchedule = new gameSchedule();
        gameSchedule.setId(gameScheduleDto.scheduleId());
        gameSchedule.setSeason(gameScheduleDto.season());
        gameSchedule.setSport(gameScheduleDto.sport());

        return gameSchedule;
    }
}
