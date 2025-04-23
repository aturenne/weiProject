package edu.tcu.cs.backend.CrewedUser;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.tcu.cs.backend.CrewList.crewList;
import jakarta.persistence.*;

@Entity
public class crewedUser {
    @Id
    private Integer crewedUserId;

    private Integer userId;


    private String position;

    private String fullName;

    private String reportTime;

    private String reportLocation;


    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @JsonBackReference
    private crewList crewList;

    public crewedUser() {

    }

    public crewList getCrewList() {
        return crewList;
    }

    public void setCrewList(crewList crewList) {
        this.crewList = crewList;
    }



    public Integer getCrewedUserId() {
        return crewedUserId;
    }

    public void setCrewedUserId(Integer crewedUserId) {
        this.crewedUserId = crewedUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }



}

