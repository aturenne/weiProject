package edu.tcu.cs.backend.crewList;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/crewList")
public class crewListController {
    private final crewListService crewListService;

    public crewListController(crewListService crewListService) {
        this.crewListService = crewListService;
    }

    public result


    }
}