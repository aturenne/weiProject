package edu.tcu.cs.backend.Availability;

import edu.tcu.cs.backend.System.Exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class availabilityServiceTest {

    @Mock
    private availabilityRepository availabilityRepository;

    @InjectMocks
    private availabilityService availabilityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateAvailability() {
        // Mock existing availability
        availability existingAvailability = new availability();
        existingAvailability.setUserId(1);
        existingAvailability.setGameId(123);
        existingAvailability.setAvailability(false);
        existingAvailability.setComments("Not available");

        // Mock updated availability
        availability updatedAvailability = new availability();
        updatedAvailability.setUserId(1);
        updatedAvailability.setGameId(123);
        updatedAvailability.setAvailability(true);
        updatedAvailability.setComments("Available");

        // Mock repository behavior
        when(availabilityRepository.findById(1)).thenReturn(Optional.of(existingAvailability));
        when(availabilityRepository.save(any(availability.class))).thenReturn(updatedAvailability);

        // Call the service method
        availability result = availabilityService.updateAvailability(updatedAvailability);

        // Verify the result
        assertEquals(1, result.getUserId());
        assertEquals(123, result.getGameId());
        assertEquals(true, result.getAvailability());
        assertEquals("Available", result.getComments());
    }

    @Test
    void updateAvailability_NotFound() {
        // Mock updated availability
        availability updatedAvailability = new availability();
        updatedAvailability.setUserId(1);
        updatedAvailability.setGameId(123);
        updatedAvailability.setAvailability(true);

        // Mock repository behavior
        when(availabilityRepository.findById(1)).thenReturn(Optional.empty());

        // Verify exception is thrown
        assertThrows(ObjectNotFoundException.class, () -> availabilityService.updateAvailability(updatedAvailability));
    }
}