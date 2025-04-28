package edu.tcu.cs.backend.GameSchedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.System.StatusCode;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class gameScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    gameScheduleService gameScheduleService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

    List<gameSchedule> schedules;

    @BeforeEach
    void setUp() {
        this.schedules = new ArrayList<>();

        gameSchedule schedule1 = new gameSchedule();
        schedule1.setId(1);
        schedule1.setSport("Soccer");
        schedule1.setSeason("Fall");
        this.schedules.add(schedule1);

        gameSchedule schedule2 = new gameSchedule();
        schedule2.setId(2);
        schedule2.setSport("Basketball");
        schedule2.setSeason("Winter");
        this.schedules.add(schedule2);
    }



    @Test
    void testSaveGameSchedule() throws Exception {
        gameSchedule newSchedule = new gameSchedule();
        newSchedule.setId(3);
        newSchedule.setSport("Tennis");
        newSchedule.setSeason("Spring");

        given(this.gameScheduleService.save(Mockito.any(gameSchedule.class))).willReturn(newSchedule);

        String json = this.objectMapper.writeValueAsString(newSchedule);

        this.mockMvc.perform(post(this.baseUrl + "/gameSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Game Schedule created successfully"))
                .andExpect(jsonPath("$.data.id").value(3))
                .andExpect(jsonPath("$.data.sport").value("Tennis"))
                .andExpect(jsonPath("$.data.season").value("Spring"));
    }
}