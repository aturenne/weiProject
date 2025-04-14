package edu.tcu.cs.backend.UserDto;
import edu.tcu.cs.backend.System.Result;

import edu.tcu.cs.backend.System.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crewMember/{userId}")
    public Result findUserById(@PathVariable int userId) {
        UserDto foundUser = this.userService.findUserById(userId);
        return new Result(true, StatusCode.SUCCESS, "Find Success", foundUser) {
        };
    }




}
