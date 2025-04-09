package edu.tcu.cs.backend.UserDto;

import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findUserById() {
        //Given. Arrange inputs and Targets. Define the behavior of Mock Object
        UserDto user1 = new UserDto();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");

        given(userRepository.findById(1)).willReturn(Optional.of(user1));
        //When. Call the method under test
        UserDto returnedUser = userService.findUserById(1);

        //Then. Verify the result
        assertThat(returnedUser.getId()).isEqualTo(user1.getId());
        assertThat(returnedUser.getFirstName()).isEqualTo(user1.getFirstName());
        assertThat(returnedUser.getLastName()).isEqualTo(user1.getLastName());
        assertThat(returnedUser.getEmail()).isEqualTo(user1.getEmail());
        verify(userRepository, times(1)).findById(1);
        

    }
}