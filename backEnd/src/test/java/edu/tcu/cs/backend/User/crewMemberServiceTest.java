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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    List<crewMember> crewMembers;



    @BeforeEach
    void setUp() {
        crewMember c1 = new crewMember();
        c1.setId(1);
        c1.setFirstName("John");
        c1.setLastName("Doe");

        crewMember c2 = new crewMember();
        c2.setId(2);
        c2.setFirstName("Jane");
        c2.setLastName("Smith");

        crewMember c3 = new crewMember();
        c3.setId(3);
        c3.setFirstName("Alice");
        c3.setLastName("Johnson");

        crewMembers = new ArrayList<>();
        crewMembers.add(c1);
        crewMembers.add(c2);
        crewMembers.add(c3);
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

    @Test
    void testFindAllUsers() {
        given(this.userRepository.findAll()).willReturn(this.crewMembers);
        List<crewMember> foundUsers = this.userService.findAll();
        assertThat(foundUsers.size()).isEqualTo(this.crewMembers.size());
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        crewMember newUser = new crewMember();
        newUser.setId(4);
        newUser.setFirstName("Bob");
        newUser.setLastName("Brown");
        newUser.setEmail("bob@brown.com");
        newUser.setPhoneNumber("1234567890");
        newUser.setRole("USER");
        newUser.setPositions(List.of("ACTOR", "EDITOR"));


        given(userRepository.save(newUser)).willReturn(newUser);

        crewMember returnedUser = userService.save(newUser);

        assertThat(returnedUser.getId()).isEqualTo(newUser.getId());
        assertThat(returnedUser.getFirstName()).isEqualTo(newUser.getFirstName());
        assertThat(returnedUser.getLastName()).isEqualTo(newUser.getLastName());
        assertThat(returnedUser.getEmail()).isEqualTo(newUser.getEmail());
        assertThat(returnedUser.getPhoneNumber()).isEqualTo(newUser.getPhoneNumber());
        assertThat(returnedUser.getRole()).isEqualTo(newUser.getRole());
        assertThat(returnedUser.getPositions()).isEqualTo(newUser.getPositions());
        verify(userRepository, times(1)).save(newUser);
    }
}