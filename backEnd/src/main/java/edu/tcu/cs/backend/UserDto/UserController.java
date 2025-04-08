package edu.tcu.cs.backend.UserDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.transform.Result;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crewMember/{userId}")
    public Result findUserById(@PathVariable int userId) {
        return null;
    }




}
