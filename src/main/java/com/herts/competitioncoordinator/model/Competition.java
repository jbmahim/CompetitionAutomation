package com.herts.competitioncoordinator.model;

import java.time.LocalDate;

public class Competition {
    private String competitionId;
    private String name;
    private String date;
    private String location;
    private String type; // Individual or Team

    public Competition() {
    }

    public Competition(String competitionId, String name, String date, String location, String type) {
        this.competitionId = competitionId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.type = type;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "competitionId='" + competitionId + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
