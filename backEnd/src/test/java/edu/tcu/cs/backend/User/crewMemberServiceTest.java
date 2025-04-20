package edu.tcu.cs.backend.User;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class crewMemberServiceTest {

    @Mock
    crewMemberRepository userRepository;

    @InjectMocks
    crewMemberService userService;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findUserById() {
        //Given. Arrange inputs and Targets. Define the behavior of Mock Object
        crewMember user1 = new crewMember();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");

        given(userRepository.findById(1)).willReturn(Optional.of(user1));
        //When. Call the method under test
        crewMember returnedUser = userService.findUserById(1);

        //Then. Verify the result
        assertThat(returnedUser.getId()).isEqualTo(user1.getId());
        assertThat(returnedUser.getFirstName()).isEqualTo(user1.getFirstName());
        assertThat(returnedUser.getLastName()).isEqualTo(user1.getLastName());
        assertThat(returnedUser.getEmail()).isEqualTo(user1.getEmail());
        verify(userRepository, times(1)).findById(1);
    }
    @Test
    void testFindUserByIdNotFound() {
        given(userRepository.findById(Mockito.anyInt())).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> userService.findUserById(1));

        assertThat(thrown).isInstanceOf(crewMemberNotFoundException.class)
                .hasMessageContaining("Could not find user 1");
        verify(userRepository, times(1)).findById(1);

    }
}