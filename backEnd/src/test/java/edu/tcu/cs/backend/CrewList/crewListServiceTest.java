package edu.tcu.cs.backend.CrewList;

import edu.tcu.cs.backend.CrewedUser.crewedUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class crewListServiceTest {

    @Mock
    crewListRepository crewListRepository;

    @InjectMocks
    crewListService crewListService;


    @BeforeEach
    void setUp() {
        crewList cl1 = new crewList();
        cl1.setGameId(1);
        cl1.setGameDate("2023-10-01");
        cl1.setGameStart("10:00:00");
        cl1.setVenue("Stadium A");
        cl1.setOpponent("Team B");
        cl1.setCrewedUsers(new ArrayList<>());  // Initialize the list

        crewedUser cu1 = new crewedUser();
        cu1.setUserId(1);
        cu1.setFullName("John Doe");
        cu1.setReportTime("09:00:00");
        cu1.setReportLocation("Stadium A");
        cu1.setPosition("DIRECTOR");
        cu1.setCrewList(cl1);  // Set the relationship

        crewedUser cu2 = new crewedUser();
        cu2.setUserId(2);
        cu2.setFullName("Jane Smith");
        cu2.setReportTime("09:30:00");
        cu2.setReportLocation("Stadium A");
        cu2.setPosition("ACTOR");
        cu2.setCrewList(cl1);  // Set the relationship

        crewedUser cu3 = new crewedUser();
        cu3.setUserId(3);
        cu3.setFullName("Alice Johnson");
        cu3.setReportTime("09:15:00");
        cu3.setReportLocation("Stadium A");
        cu3.setPosition("DIRECTOR");
        cu3.setCrewList(cl1);  // Set the relationship

        cl1.addCrewedUser(cu1);
        cl1.addCrewedUser(cu2);
        cl1.addCrewedUser(cu3);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void TestFindCrewListByGameId() {
        crewList cl1 = new crewList();
        cl1.setGameId(1);
        cl1.setGameDate("2023-10-01");
        cl1.setGameStart("10:00:00");
        cl1.setVenue("Stadium A");
        cl1.setOpponent("Team B");
        cl1.setCrewedUsers(new ArrayList<>());  // Initialize the list
        crewedUser cu1 = new crewedUser();
        cu1.setUserId(1);
        cu1.setFullName("John Doe");
        cu1.setReportTime("09:00:00");
        cu1.setReportLocation("Stadium A");
        cu1.setPosition("DIRECTOR");
        cu1.setCrewList(cl1);  // Set the relationship
        crewedUser cu2 = new crewedUser();
        cu2.setUserId(2);
        cu2.setFullName("Jane Smith");
        cu2.setReportTime("09:30:00");
        cu2.setReportLocation("Stadium A");
        cu2.setPosition("ACTOR");
        cu2.setCrewList(cl1);  // Set the relationship

        cl1.addCrewedUser(cu1);
        cl1.addCrewedUser(cu2);

        given(crewListRepository.findById(1)).willReturn(Optional.of(cl1));

        crewList returnedCrewList = crewListService.findCrewListByGameId(1);

        assertThat(returnedCrewList.getGameId()).isEqualTo(cl1.getGameId());
        assertThat(returnedCrewList.getGameDate()).isEqualTo(cl1.getGameDate());
        assertThat(returnedCrewList.getGameStart()).isEqualTo(cl1.getGameStart());
        assertThat(returnedCrewList.getVenue()).isEqualTo(cl1.getVenue());
        assertThat(returnedCrewList.getOpponent()).isEqualTo(cl1.getOpponent());
        assertThat(returnedCrewList.getCrewedUsers()).isEqualTo(cl1.getCrewedUsers());

        verify(crewListRepository, times(1)).findById(1);

    }
}

