package com.herts.competitioncoordinator.model;

public class Official {
    private String officialId;
    private String name;
    private String email;
    private String password;
    private String category;

    public Official() {
    }

    public Official(String officialId, String name, String email, String password, String category) {
        this.officialId = officialId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    public Official(String name, String email, String password, String category) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
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

    @Override
    public String toString() {
        return "Official{" +
                "officialId='" + officialId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
