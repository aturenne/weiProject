    package edu.tcu.cs.backend.CrewList;

    import edu.tcu.cs.backend.System.Exception.ObjectNotFoundException;
    import edu.tcu.cs.backend.CrewedUser.Dto.CrewedUserDto;
    import edu.tcu.cs.backend.System.StatusCode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import edu.tcu.cs.backend.CrewedUser.crewedUser;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.http.MediaType;

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
    class crewListControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        crewListService crewListService;
        List<crewList> crewList;

        @Autowired
        ObjectMapper objectMapper;

        @Value("${api.endpoint.base-url}")
        String baseUrl;

        @BeforeEach
        void setUp() {
            this.crewList = new ArrayList<>();

            crewList crew1 = new crewList();
            crew1.setGameId(1);
            crew1.setGameDate("2023-10-01");
            crew1.setOpponent("Team A");
            crew1.setVenue("Stadium A");
            crew1.setGameStart("10:00 AM");
            crew1.setCrewedUsers(new ArrayList<>());  // Initialize the list

            crewedUser user1 = new crewedUser();
            user1.setUserId(1);
            user1.setFullName("John Doe");
            user1.setCrewList(crew1);
            user1.setReportLocation("Location A");
            user1.setReportTime("9:00 AM");
            user1.setPosition("DIRECTOR");

            crew1.addCrewedUser(user1);
            crewList.add(crew1);

            crewList crew2 = new crewList();
            crew2.setGameId(2);
            crew2.setGameDate("2023-10-01");
            crew2.setOpponent("Team A");
            crew2.setVenue("Stadium A");
            crew2.setGameStart("10:00 AM");
            crew2.setCrewedUsers(new ArrayList<>());  // Initialize the list

            crewedUser user2 = new crewedUser();
            user2.setUserId(2);
            user2.setFullName("Jane Doe");
            user2.setCrewList(crew2);
            user2.setReportLocation("Location A");
            user2.setReportTime("9:00 AM");
            user2.setPosition("DIRECTOR");
            crew2.addCrewedUser(user2);

            crewedUser user3 = new crewedUser();
            user3.setUserId(3);
            user3.setFullName("Jim Doe");
            user3.setCrewList(crew2);
            user3.setReportLocation("Location A");
            user3.setReportTime("9:00 AM");
            user3.setPosition("DIRECTOR");
            crew2.addCrewedUser(user3);
            crewList.add(crew2);

        }

        @AfterEach
        void tearDown() {

        }

        @Test
        void TestFindCrewListByGameIDSuccess() throws Exception {
            given(this.crewListService.findCrewListByGameId(2)).willReturn(this.crewList.get(1));

            this.mockMvc.perform(get(this.baseUrl+ "/crewList/2").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.flag").value(true))
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.message").value("Find Success"))
                    .andExpect(jsonPath("$.data.gameId").value(2))
                    .andExpect(jsonPath("$.data.gameDate").value("2023-10-01"));

        }
    }

