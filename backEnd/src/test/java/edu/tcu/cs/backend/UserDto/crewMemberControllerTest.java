package edu.tcu.cs.backend.UserDto;

import edu.tcu.cs.backend.System.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class crewMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    crewMemberService userService;


    List<crewMember> users;

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

    @AfterEach
    void tearDown() {

    }

    @Test
    void testFindUserByIdSuccess() throws Exception {
        //Given
        given(this.userService.findUserById(1)).willReturn(this.users.get(0));
        //When
        this.mockMvc.perform(get("/crewMember/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"));

    }

    @Test
    void testFindUserByIdNotFound() throws Exception {
        //Given
        given(this.userService.findUserById(5689)).willThrow(new crewMemberNotFoundException(5689));
        //When
        this.mockMvc.perform(get("/crewMember/5689").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Could not find user 5689"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
