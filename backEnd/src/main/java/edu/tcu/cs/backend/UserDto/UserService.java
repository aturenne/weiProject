package edu.tcu.cs.backend.UserDto;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserById(int userId) {
        return this.userRepository.findById(userId).get();
    }
}
