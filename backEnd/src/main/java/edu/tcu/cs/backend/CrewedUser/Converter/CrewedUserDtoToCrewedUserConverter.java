
package edu.tcu.cs.backend.CrewedUser.Converter;

import edu.tcu.cs.backend.CrewList.crewList;
import edu.tcu.cs.backend.CrewedUser.crewedUser;
import edu.tcu.cs.backend.CrewedUser.Dto.CrewedUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CrewedUserDtoToCrewedUserConverter implements Converter<CrewedUserDto, crewedUser> {

    @Override
    public crewedUser convert(CrewedUserDto source) {
        crewedUser crewedUser = new crewedUser();
        crewedUser.setCrewedUserId(source.crewedUserId());
        crewedUser.setUserId(source.userId());

        crewedUser.setPosition(source.position());
        crewedUser.setFullName(source.fullName());
        crewedUser.setReportTime(source.reportTime());
        crewedUser.setReportLocation(source.reportLocation());
        return crewedUser;
    }

}

