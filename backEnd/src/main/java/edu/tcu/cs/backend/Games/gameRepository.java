package edu.tcu.cs.backend.Games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface gameRepository extends JpaRepository<game, Integer> {

}
