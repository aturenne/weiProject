package edu.tcu.cs.backend.User;

public class crewMemberNotFoundException extends RuntimeException {
    public crewMemberNotFoundException(int userId) {
        super("Could not find user " + userId);

    }
}
