package edu.tcu.cs.backend.crewList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface crewListRepository extends JpaRepository<crewList, Integer> {
}