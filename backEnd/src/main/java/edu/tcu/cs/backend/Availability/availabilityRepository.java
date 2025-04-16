package edu.tcu.cs.backend.Availability;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface availabilityRepository extends JpaRepository<availability, Integer> {
    Optional<availability> findByUserIdAndGameId(int userId, int gameId);
}