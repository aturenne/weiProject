package edu.tcu.cs.backend.UserDto;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface crewMemberRepository extends JpaRepository<crewMember, Integer> {

}
