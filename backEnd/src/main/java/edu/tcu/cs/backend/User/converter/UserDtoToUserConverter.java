package edu.tcu.cs.backend.User.converter;

import edu.tcu.cs.backend.User.crewMember;
import edu.tcu.cs.backend.User.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, crewMember> {

    @Override
    public crewMember convert(UserDto source) {
        crewMember user = new crewMember();
        user.setId(source.id());
        user.setFirstName(source.firstName());
        user.setLastName(source.lastName());
        user.setEmail(source.email());
        user.setPhoneNumber(source.phoneNumber());
        user.setRole(source.role());
        return user;
    }
}
