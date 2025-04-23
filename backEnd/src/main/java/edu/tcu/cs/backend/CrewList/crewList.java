package edu.tcu.cs.backend.CrewList;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.tcu.cs.backend.CrewedUser.crewedUser;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class crewList {

    @Id
    private Integer gameId;

    private String gameStart;

    private String gameDate;

    private String venue;

    private String opponent;




    @OneToMany(mappedBy = "crewList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<crewedUser> crewedUsers = new ArrayList<>();

    // Default constructor
    public crewList() {
    }

    // Getters and Setters
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameStart() {
        return gameStart;
    }

    public void setGameStart(String gameStart) {
        this.gameStart = gameStart;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public void addCrewedUser(crewedUser crewedUser) {
        crewedUsers.add(crewedUser);
        crewedUser.setCrewList(this);
    }

    public List<crewedUser> getCrewedUsers() {
        return crewedUsers;
    }

    public void setCrewedUsers(List<crewedUser> crewedUsers) {
        this.crewedUsers = crewedUsers;
    }
}

