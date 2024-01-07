package com.herts.competitioncoordinator.model;

public class Staff extends Official {
    private String staffId;
    private String role;

    public Staff() {
    }

    public Staff(String staffId, String name, String email, String password, String category, String role) {
        super(name, email, password, category);
        this.staffId = staffId;
        this.role = role;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String officialId) {
        this.staffId = officialId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
