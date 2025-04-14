package edu.tcu.cs.backend.UserDto;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final crewMemberRepository userRepository;

    public UserService(crewMemberRepository userRepository) {
        this.userRepository = userRepository;
    }

    public crewMember findUserById(int userId) {
        return this.userRepository.findById(userId).orElseThrow(()-> new crewMemberNotFoundException(userId));
    }
}
