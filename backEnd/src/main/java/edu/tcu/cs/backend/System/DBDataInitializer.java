package edu.tcu.cs.backend.System;

import edu.tcu.cs.backend.User.crewMember;
import edu.tcu.cs.backend.User.crewMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final crewMemberRepository crewMemberRepository;

    public DBDataInitializer(crewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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

    }
}
