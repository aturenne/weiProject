package edu.tcu.cs.backend.CrewList.Converter;

import edu.tcu.cs.backend.CrewList.crewList;
import edu.tcu.cs.backend.CrewList.Dto.CrewListDto;
import edu.tcu.cs.backend.CrewedUser.crewedUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CrewListDtoToCrewListConverter implements Converter<CrewListDto, crewList> {

    @Override
    public crewList convert(CrewListDto source) {
        crewList entity = new crewList();
        entity.setGameId(source.gameId());
        entity.setGameStart(source.gameStart());
        entity.setGameDate(source.gameDate());
        entity.setVenue(source.venue());
        entity.setOpponent(source.opponent());
        return entity;
    }
}

