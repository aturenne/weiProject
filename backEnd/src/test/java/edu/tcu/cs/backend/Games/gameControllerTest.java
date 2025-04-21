package edu.tcu.cs.backend.Games;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.Games.Dto.GameDto;
import edu.tcu.cs.backend.System.StatusCode;
import edu.tcu.cs.backend.System.Exception.ObjectNotFoundException;
import org.hamcrest.Matchers;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class gameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    gameService gameService;

    @Autowired
    ObjectMapper objectMapper;

    List<game> games;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

    @BeforeEach
    void setUp() {
        this.games = new ArrayList<>();

        game g1 = new game();
        g1.setGameId(1);
        g1.setScheduleId(1);
        g1.setVenue("Stadium A");
        g1.setGameDate("2023-10-01");
        g1.setOpponent("Team B");
        g1.setIsFinalized(false);
        this.games.add(g1);

        game g2 = new game();
        g2.setGameId(2);
        g2.setScheduleId(1);
        g2.setVenue("Stadium B");
        g2.setGameDate("2023-10-02");
        g2.setOpponent("Team C");
        g2.setIsFinalized(false);
        this.games.add(g2);

        game g3 = new game();
        g3.setGameId(3);
        g3.setScheduleId(2);
        g3.setVenue("Stadium C");
        g3.setGameDate("2023-10-03");
        g3.setOpponent("Team D");
        g3.setIsFinalized(false);
        this.games.add(g3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindAllGames() throws Exception {
        given(this.gameService.findAll()).willReturn(this.games);

        this.mockMvc.perform(get(this.baseUrl + "/games").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.games.size())))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].firstName").value("John"))
                .andExpect(jsonPath("$.data[0].lastName").value("Doe"));
    }
}