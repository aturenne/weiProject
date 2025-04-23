package edu.tcu.cs.backend.CrewedUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface crewedUserRepository extends JpaRepository<crewedUser, Integer> {
}

