package edu.tcu.cs.backend.UserDto;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int userId) {
        super("Could not find user " + userId);

    }
}
