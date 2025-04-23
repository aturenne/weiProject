package edu.tcu.cs.backend.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.backend.User.dto.UserDto;
import edu.tcu.cs.backend.System.StatusCode;
import edu.tcu.cs.backend.System.Exception.ObjectNotFoundException;
import org.hamcrest.Matchers;
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
class crewMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    crewMemberService userService;

    List<crewMember> users;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

    @BeforeEach
    void setUp() {
        this.users = new ArrayList<>();

        crewMember user1 = new crewMember();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");
        
        users.add(user1);

        crewMember user2 = new crewMember();
        user2.setId(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane@doe.com");
        users.add(user2);

        crewMember user3 = new crewMember();
        user3.setId(3);
        user3.setFirstName("Jim");
        user3.setLastName("Beam");
        user3.setEmail("jim@beam.com");
        users.add(user3);
    }

    @Test
    void testFindUserByIdSuccess() throws Exception {
        // Given
        given(this.userService.findUserById(2)).willReturn(this.users.get(1));
        // When
        this.mockMvc.perform(get(this.baseUrl+ "/crewMember/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.id").value(2))
                .andExpect(jsonPath("$.data.firstName").value("Jane"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"));
    }

    @Test
    void testFindUserByIdNotFound() throws Exception {
        // Given
        given(this.userService.findUserById(5689)).willThrow(new crewMemberNotFoundException(5689));
        // When
        this.mockMvc.perform(get(this.baseUrl + "/crewMember/5689").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Could not find user 5689"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testFindAllUsers() throws Exception {
        // Given
        given(this.userService.findAll()).willReturn(this.users);
        // When
        this.mockMvc.perform(get(this.baseUrl + "/crewMember").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.users.size())))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].firstName").value("John"))
                .andExpect(jsonPath("$.data[0].lastName").value("Doe"));
    }

    @Test
    void testAddUser() throws Exception {
        crewMember user = new crewMember();
        user.setId(4);
        user.setFirstName("Jack");
        user.setLastName("Daniels");
        user.setEmail("jack@daniels.com");
        user.setPhoneNumber("1234567890");
        user.setRole("USER");
        user.setPositions(List.of("DIRECTOR", "PRODUCER"));

        String json = this.objectMapper.writeValueAsString(user);

        given(this.userService.save(Mockito.any(crewMember.class))).willReturn(user);

        this.mockMvc.perform(post(this.baseUrl + "/crewMember").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Create Success"))
                .andExpect(jsonPath("$.data.id").value(4))
                .andExpect(jsonPath("$.data.firstName").value("Jack"))
                .andExpect(jsonPath("$.data.lastName").value("Daniels"));
    }

}