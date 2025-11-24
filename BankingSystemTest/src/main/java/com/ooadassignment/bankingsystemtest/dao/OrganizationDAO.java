package com.ooadassignment.bankingsystemtest.dao;

import com.ooadassignment.bankingsystemtest.model.Organization;
import com.ooadassignment.bankingsystemtest.util.DBConnection;

import java.sql.*;
import java.util.*;

public class OrganizationDAO {

    public Organization authenticateOrganization(int organizationId, String organizationName) throws SQLException {
        String sql = "SELECT organization_id, organization_name FROM organizations WHERE organization_id = ? AND LOWER(organization_name) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, organizationId);
            stmt.setString(2, organizationName.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapOrganization(rs);
                }
            }
        }
        return null;
    }

    public List<Organization> fetchOrganizations() throws SQLException {
        List<Organization> organizations = new ArrayList<>();
        String sql = "SELECT organization_id, organization_name FROM organizations ORDER BY organization_name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                organizations.add(mapOrganization(rs));
            }
        }
        return organizations;
    }

    private Organization mapOrganization(ResultSet rs) throws SQLException {
        return new Organization(rs.getInt("organization_id"), rs.getString("organization_name"));
    }
}
