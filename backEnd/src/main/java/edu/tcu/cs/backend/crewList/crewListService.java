package edu.tcu.cs.backend.crewList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class crewListService {
    private final crewListRepository crewListRepository;

    @Autowired
    public crewListService(crewListRepository crewListRepository) {
        this.crewListRepository = crewListRepository;
    }

    public List<crewList> getAllCrewMembers() {
        return crewListRepository.findAll();
    }

    
}