package com.ooadassignment.bankingsystemtest.session;

import com.ooadassignment.bankingsystemtest.model.Organization;

public class OrganizationSession {

    private static OrganizationSession instance;
    private Organization loggedInOrganization;

    private OrganizationSession() {}

    public static OrganizationSession getInstance() {
        if (instance == null) {
            instance = new OrganizationSession();
        }
        return instance;
    }

    public Organization getLoggedInOrganization() {
        return loggedInOrganization;
    }

    public void setLoggedInOrganization(Organization loggedInOrganization) {
        this.loggedInOrganization = loggedInOrganization;
    }

    public void clearSession() {
        loggedInOrganization = null;
    }
}
