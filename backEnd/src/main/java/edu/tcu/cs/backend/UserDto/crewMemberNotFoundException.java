package edu.tcu.cs.backend.UserDto;

public class crewMemberNotFoundException extends RuntimeException {
    public crewMemberNotFoundException(int userId) {
        super("Could not find user " + userId);

    }
}
