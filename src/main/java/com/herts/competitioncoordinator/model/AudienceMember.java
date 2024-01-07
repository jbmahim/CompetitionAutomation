package com.herts.competitioncoordinator.model;

import java.time.LocalDate;

public class AudienceMember {
    private String audienceId;
    private String name;
    private LocalDate dob;

    public AudienceMember(){

    }
    public AudienceMember(String audienceId, String name, LocalDate dob) {
        this.audienceId = audienceId;
        this.name = name;
        this.dob = dob;
    }

    public String getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
