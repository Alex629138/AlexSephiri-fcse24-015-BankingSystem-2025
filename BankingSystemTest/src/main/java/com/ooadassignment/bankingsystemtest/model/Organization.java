package com.ooadassignment.bankingsystemtest.model;

public class Organization {

    private int organizationId;
    private String organizationName;

    public Organization() {
    }

    public Organization(int organizationId, String organizationName) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return organizationName;
    }
}
