package edu.tcu.cs.backend.CrewList;

import edu.tcu.cs.backend.User.crewMemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class crewListService {
    private final crewListRepository crewListRepository;

    @Autowired
    public crewListService(crewListRepository crewListRepository) {
        this.crewListRepository = crewListRepository;
    }

    public crewList findCrewListByGameId(int gameId) {
        return this.crewListRepository.findById(gameId).orElseThrow(()-> new crewMemberNotFoundException(gameId));

    }

    
}

