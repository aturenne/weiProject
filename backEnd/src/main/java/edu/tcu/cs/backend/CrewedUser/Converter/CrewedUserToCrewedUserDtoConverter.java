package edu.tcu.cs.backend.CrewedUser.Converter;

import edu.tcu.cs.backend.CrewList.Converter.CrewListToCrewListDtoConverter;
import edu.tcu.cs.backend.CrewedUser.crewedUser;
import edu.tcu.cs.backend.CrewedUser.Dto.CrewedUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewedUserToCrewedUserDtoConverter implements Converter<crewedUser, CrewedUserDto> {

    @Override
    public CrewedUserDto convert(crewedUser source) {
        return new CrewedUserDto(
                source.getUserId(),
                source.getPosition(),
                source.getFullName(),
                source.getReportTime(),
                source.getReportLocation()
        );
    }
}

