package edu.tcu.cs.backend.User.dto;

import jakarta.validation.constraints.NotEmpty;


public record UserDto (Integer id, @NotEmpty(message = "name is required") String firstName, @NotEmpty(message = "Last Name is requried") String lastName,
                       @NotEmpty (message = "Email is required") String email, @NotEmpty (message = "Phone Number is required") String phoneNumber,
                       @NotEmpty (message = "Role is required") String role) {
}
