package edu.tcu.cs.backend.System;
/*
import edu.tcu.cs.backend.CrewList.crewList;
import edu.tcu.cs.backend.CrewList.crewListRepository;
import edu.tcu.cs.backend.CrewedUser.crewedUser;
import edu.tcu.cs.backend.CrewedUser.crewedUserRepository;

 */
import edu.tcu.cs.backend.User.crewMember;
import edu.tcu.cs.backend.User.crewMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final crewMemberRepository crewMemberRepository;

    public DBDataInitializer(crewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }
    //private final crewListRepository crewListRepository;
   // private final crewedUserRepository crewedUserRepository;

    /*
    public DBDataInitializer(crewMemberRepository crewMemberRepository,
                             crewListRepository crewListRepository,
                             crewedUserRepository crewedUserRepository) {
        this.crewMemberRepository = crewMemberRepository;
        this.crewListRepository = crewListRepository;
        this.crewedUserRepository = crewedUserRepository;
    }

     */

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create and save crew members
        crewMember cm1 = new crewMember();
        cm1.setId(1);
        cm1.setFirstName("John");
        cm1.setLastName("Doe");
        cm1.setEmail("john.doe@gmail.com");
        cm1.setPhoneNumber("1234567890");
        cm1.setRole("ADMIN");
        cm1.setPositions(List.of("DIRECTOR", "PRODUCER"));

        crewMember cm2 = new crewMember();
        cm2.setId(2);
        cm2.setFirstName("Jane");
        cm2.setLastName("Smith");
        cm2.setEmail("jane.smith@gmail.com");
        cm2.setPhoneNumber("0987654321");
        cm2.setRole("USER");
        cm2.setPositions(List.of("ACTOR", "EDITOR"));

        crewMember cm3 = new crewMember();
        cm3.setId(3);
        cm3.setFirstName("Alice");
        cm3.setLastName("Johnson");
        cm3.setEmail("alice.johnson@gmail.com");
        cm3.setPhoneNumber("1122334455");
        cm3.setRole("USER");
        cm3.setPositions(List.of("DIRECTOR", "PRODUCER"));

        crewMemberRepository.save(cm1);
        crewMemberRepository.save(cm2);
        crewMemberRepository.save(cm3);
/*
        // Create crew list with initialized crewedUsers list
        crewList cl1 = new crewList();
        cl1.setGameId(1);
        cl1.setGameStart("10:00:00");
        cl1.setGameDate("2023-10-01");
        cl1.setVenue("Stadium A");
        cl1.setOpponent("Team A");

        List<crewedUser> crewedUsers = new ArrayList<>();
        cl1.setCrewedUsers(crewedUsers);

        // Create crewed users and set up relationships
        crewedUser cu1 = new crewedUser();
        cu1.setCrewedUserId(1);
        cu1.setUserId(1);
        cu1.setFullName("John Doe");
        cu1.setReportTime("09:00:00");
        cu1.setReportLocation("Stadium A");
        cu1.setPosition("DIRECTOR");
        cu1.setCrewList(cl1);  // Set the relationship

        crewedUser cu2 = new crewedUser();
        cu2.setCrewedUserId(2);
        cu2.setUserId(2);
        cu2.setFullName("Jane Smith");
        cu2.setReportTime("09:30:00");
        cu2.setReportLocation("Stadium A");
        cu2.setPosition("ACTOR");
        cu2.setCrewList(cl1);  // Set the relationship

        crewedUser cu3 = new crewedUser();
        cu3.setCrewedUserId(3);
        cu3.setUserId(3);
        cu3.setFullName("Alice Johnson");
        cu3.setReportTime("09:15:00");
        cu3.setReportLocation("Stadium A");
        cu3.setPosition("DIRECTOR");
        cu3.setCrewList(cl1);  // Set the relationship

        // Add all users to the list
        crewedUsers.add(cu1);
        crewedUsers.add(cu2);
        crewedUsers.add(cu3);

        // Save the crew list which will cascade to crewed users
        crewListRepository.save(cl1);

 */
    }
}