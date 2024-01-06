package com.herts.competitioncoordinator.model;

import java.time.LocalDate;

public class Competitor {
    private String id;
    private String name;
    private String email;
    private String password;
    private String category;
    private String level;
    private String sportsName;

    public Competitor() {
    }

    public Competitor(String id, String name, String email, String password, String category, String level, String sportsName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.category = category;
        this.level = level;
        this.sportsName = sportsName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", category='" + category + '\'' +
                ", level='" + level + '\'' +
                ", sportsName='" + sportsName + '\'' +
                '}';
    }
}
