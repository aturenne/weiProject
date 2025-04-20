package edu.tcu.cs.backend.User.converter;

import edu.tcu.cs.backend.User.dto.UserDto;
import edu.tcu.cs.backend.User.crewMember;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<crewMember, UserDto> {

    @Override
    public UserDto convert(crewMember source) {
        UserDto userDto = new UserDto(source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail(),
                source.getPhoneNumber(),
                source.getRole());
        return userDto;
    }
}
