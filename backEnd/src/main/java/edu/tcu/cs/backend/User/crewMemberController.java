package edu.tcu.cs.backend.User;

import edu.tcu.cs.backend.System.Result;
import edu.tcu.cs.backend.System.StatusCode;
import edu.tcu.cs.backend.User.converter.UserToUserDtoConverter;
import edu.tcu.cs.backend.User.converter.UserDtoToUserConverter;
import edu.tcu.cs.backend.User.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("${api.endpoint.base-url}")
public class crewMemberController {

    private final crewMemberService userService;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    public crewMemberController(crewMemberService userService, UserToUserDtoConverter userToUserDtoConverter, UserDtoToUserConverter userDtoToUserConverter) {
        this.userService = userService;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
    }


    @GetMapping("/crewMember")
    public Result findAllUsers() {
        List<crewMember> foundUsers = this.userService.findAll();

        List<UserDto> userDtos = foundUsers.stream()
                .map(userToUserDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Success", userDtos);
    }

    @GetMapping("/crewMember/{userId}")
    public Result findUserById(@PathVariable int userId) {
        crewMember foundUser = this.userService.findUserById(userId);
        UserDto userDto = this.userToUserDtoConverter.convert(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Find Success", userDto);
    }

    @PostMapping("/crewMember")
    public Result addUser(@RequestBody @Valid crewMember newCrewMember) {
        crewMember createdUser = this.userService.save(newCrewMember);
        return new Result(true, StatusCode.SUCCESS, "Create Success", createdUser);
    }

    @PostMapping("/invite")
    public Result inviteCrewMembers(@RequestBody List<Integer> crewMemberIds) {
        List<Map<String, String>> invites = new ArrayList<>();

        for (Integer id : crewMemberIds) {
            crewMember member = userService.findUserById(id);
            String token = UUID.randomUUID().toString(); // Generate a one-time token

            Map<String, String> invite = new HashMap<>();
            invite.put("crewMemberId", String.valueOf(member.getId()));
            invite.put("email", member.getEmail());
            invite.put("token", token);

            invites.add(invite);
        }

        return new Result(true, StatusCode.SUCCESS, "Invitations generated successfully", invites);
    }

    @DeleteMapping("/crewMember/{id}")
    public Result deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return new Result(true, StatusCode.SUCCESS, "Delete Success", null);
    }

}
