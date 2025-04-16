package edu.tcu.cs.backend.UserDto;
import edu.tcu.cs.backend.System.Result;

import edu.tcu.cs.backend.System.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class crewMemberController {

    private final crewMemberService userService;

    public crewMemberController(crewMemberService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Result findAllUsers() {
        List<crewMember> foundUsers = this.userService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find Success", this.userService.findAll());
    }

    @GetMapping("/crewMember/{userId}")
    public Result findUserById(@PathVariable int userId) {
        crewMember foundUser = this.userService.findUserById(userId);
        return new Result(true, StatusCode.SUCCESS, "Find Success", foundUser);
    }

    @PostMapping
    public Result addUser(@RequestBody @Valid crewMember newCrewMember) {
        crewMember createdUser = this.userService.save(newCrewMember);
        return new Result(true, StatusCode.SUCCESS, "Create Success", createdUser);
    }




}
