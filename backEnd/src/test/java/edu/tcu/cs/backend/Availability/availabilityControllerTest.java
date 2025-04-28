package edu.tcu.cs.backend.Availability;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class availabilityControllerTest {

    @MockBean
    availabilityService availabilityService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateAvailability() throws Exception {
        // Create a mock request object
        availability requestAvailability = new availability();
        requestAvailability.setUserId(1);
        requestAvailability.setGameId(123);
        requestAvailability.setAvailability(true);
        requestAvailability.setComments("Available for the game");

        // Create a mock response object
        availability updatedAvailability = new availability();
        updatedAvailability.setUserId(1);
        updatedAvailability.setGameId(123);
        updatedAvailability.setAvailability(true);
        updatedAvailability.setComments("Available for the game");

        // Mock the service behavior
        when(availabilityService.updateAvailability(any(availability.class))).thenReturn(updatedAvailability);

        // Perform PUT request and verify the response
        mockMvc.perform(
                        put(baseUrl + "/availability")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestAvailability))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.userId").value(1))
                .andExpect(jsonPath("$.data.gameId").value(123))
                .andExpect(jsonPath("$.data.availability").value(true))
                .andExpect(jsonPath("$.data.comments").value("Available for the game"));
    }
}