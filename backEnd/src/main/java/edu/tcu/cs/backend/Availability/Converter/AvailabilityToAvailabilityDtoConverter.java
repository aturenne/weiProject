package edu.tcu.cs.backend.Availability.Converter;

import edu.tcu.cs.backend.Availability.Dto.AvailabilityDTO;
import edu.tcu.cs.backend.Availability.availability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityToAvailabilityDtoConverter implements Converter<availability, AvailabilityDTO> {

    @Override
    public AvailabilityDTO convert(availability source) {
        return new AvailabilityDTO(
                source.getUserId(),
                source.getGameId(),
                source.getAvailability(),
                source.getComments()
        );
    }
}
