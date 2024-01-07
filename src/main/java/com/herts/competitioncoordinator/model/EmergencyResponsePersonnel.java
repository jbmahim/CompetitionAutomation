package com.herts.competitioncoordinator.model;

public class EmergencyResponsePersonnel extends Official{
    private String emergencyResponsePersonnelId;
    private String role;

    public EmergencyResponsePersonnel() {
    }

    public EmergencyResponsePersonnel(String emergencyResponsePersonnelId, String name, String email, String password, String category, String role) {
        super(name, email, password, category);
        this.emergencyResponsePersonnelId = emergencyResponsePersonnelId;
        this.role = role;
    }

    public String getEmergencyResponsePersonnelId() {
        return emergencyResponsePersonnelId;
    }

    public void setEmergencyResponsePersonnelId(String emergencyResponsePersonnelId) {
        this.emergencyResponsePersonnelId = emergencyResponsePersonnelId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
