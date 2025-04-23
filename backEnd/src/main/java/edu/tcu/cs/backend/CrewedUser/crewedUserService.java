
package edu.tcu.cs.backend.CrewedUser;

import edu.tcu.cs.backend.CrewList.crewList;
import edu.tcu.cs.backend.CrewList.crewListRepository;
import edu.tcu.cs.backend.CrewedUser.Dto.CrewedUserDto;
import edu.tcu.cs.backend.System.Exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class crewedUserService {

    private final crewedUserRepository crewedUserRepository;
    private final crewListRepository crewListRepository;

    @Autowired
    public crewedUserService(crewedUserRepository crewedUserRepository, crewListRepository crewListRepository) {
        this.crewedUserRepository = crewedUserRepository;
        this.crewListRepository = crewListRepository;
    }
    @Transactional
    public List<crewedUser> findAll() {
        return crewedUserRepository.findAll();
    }


}
