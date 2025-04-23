package edu.tcu.cs.backend.GameSchedule;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class gameSchedule implements Serializable {

    @Id
    private Integer id;

    private String sport;

    private String season;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
