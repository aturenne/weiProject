
package edu.tcu.cs.backend.CrewedUser;

import edu.tcu.cs.backend.System.Result;
import edu.tcu.cs.backend.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/CrewedUser")
public class crewedUserController {

    private final crewedUserService crewedUserService;

    public crewedUserController(crewedUserService crewedUserService) {
        this.crewedUserService = crewedUserService;
    }

    @GetMapping("/{gameId}")
    public Result findAllCrewedUsers() {
        List<crewedUser> foundCrewedUsers = this.crewedUserService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find Success", foundCrewedUsers);
    }


}


