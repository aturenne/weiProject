package edu.tcu.cs.backend.Availability.Converter;

import edu.tcu.cs.backend.Availability.Dto.AvailabilityDTO;
import edu.tcu.cs.backend.Availability.availability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDtoToAvailabilityConverter implements Converter<AvailabilityDTO, availability> {

    @Override
    public availability convert(AvailabilityDTO availabilityDTO) {
        availability availability = new availability();
        availability.setUserId(availabilityDTO.userId());
        availability.setGameId(availabilityDTO.gameId());
        availability.setAvailability(availabilityDTO.availability());
        availability.setComments(availabilityDTO.comments());

        return availability;
    }
}