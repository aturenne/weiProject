package edu.tcu.cs.backend.User;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class crewMemberService {
    private final crewMemberRepository userRepository;

    public crewMemberService(crewMemberRepository userRepository) {
        this.userRepository = userRepository;
    }

    public crewMember findUserById(int userId) {
        return this.userRepository.findById(userId).orElseThrow(()-> new crewMemberNotFoundException(userId));
    }

    public List<crewMember> findAll() {
        return this.userRepository.findAll();
    }

    public void deleteUserById(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new crewMemberNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

    public crewMember save(crewMember newCrewMember) {
        return this.userRepository.save(newCrewMember);
    }
}
