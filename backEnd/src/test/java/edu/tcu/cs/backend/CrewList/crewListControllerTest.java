package edu.tcu.cs.backend.CrewList;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.test.web.servlet.MockMvc;

    import java.util.ArrayList;
    import java.util.List;

    import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
    @AutoConfigureMockMvc(addFilters = false)
    class crewListControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        crewListService crewListService;

        @Autowired
        ObjectMapper objectMapper;
        List<crewList> crewList;

        @Value("${api.endpoint.base-url}")
        String baseUrl;

        @BeforeEach
        void setUp() {




        }

        @AfterEach
        void tearDown() {

        }

        @Test
        void getCrewMembers() {
            // Arrange

        }
    }

