package edu.tcu.cs.backend.CrewList;

import edu.tcu.cs.backend.System.Result;
import edu.tcu.cs.backend.System.StatusCode;
import edu.tcu.cs.backend.CrewList.Dto.CrewListDto;
import edu.tcu.cs.backend.CrewList.Converter.CrewListToCrewListDtoConverter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}")
public class crewListController {

    private final crewListService crewListService;
    private final CrewListToCrewListDtoConverter crewListToCrewListDtoConverter;

    public crewListController(crewListService crewListService, CrewListToCrewListDtoConverter crewListToCrewListDtoConverter) {
        this.crewListService = crewListService;
        this.crewListToCrewListDtoConverter = crewListToCrewListDtoConverter;
    }

    @GetMapping("/crewList/{gameId}")
    public Result findCrewListByGameId(@PathVariable int gameId) {
        crewList crewList = crewListService.findCrewListByGameId(gameId);
        CrewListDto crewListDto = crewListToCrewListDtoConverter.convert(crewList);
        return new Result(true, StatusCode.SUCCESS, "Find Success", crewListDto);
    }


}

