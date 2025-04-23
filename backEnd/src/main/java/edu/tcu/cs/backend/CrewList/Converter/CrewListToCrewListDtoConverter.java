package edu.tcu.cs.backend.CrewList.Converter;

import edu.tcu.cs.backend.CrewList.crewList;
import edu.tcu.cs.backend.CrewList.Dto.CrewListDto;
import edu.tcu.cs.backend.CrewedUser.Converter.CrewedUserToCrewedUserDtoConverter;
import edu.tcu.cs.backend.CrewedUser.Dto.CrewedUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CrewListToCrewListDtoConverter implements Converter<crewList, CrewListDto> {

    private final CrewedUserToCrewedUserDtoConverter crewedUserToCrewedUserDtoConverter;

    public CrewListToCrewListDtoConverter(CrewedUserToCrewedUserDtoConverter crewedUserToCrewedUserDtoConverter) {
        this.crewedUserToCrewedUserDtoConverter = crewedUserToCrewedUserDtoConverter;
    }

    @Override
    public CrewListDto convert(crewList source) {
        return new CrewListDto(
                source.getGameId(),
                source.getGameStart(),
                source.getGameDate(),
                source.getVenue(),
                source.getOpponent()
        );
    }
}

