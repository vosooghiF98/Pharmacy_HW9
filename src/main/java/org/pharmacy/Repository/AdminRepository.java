package org.pharmacy.Repository;

import org.pharmacy.entities.Admin;

import java.sql.SQLException;

public interface AdminRepository {
    void save(String username, String password) throws SQLException;
    Admin load(String username, String password) throws SQLException;
    void changePassword(String oldPassword, String newPassword) throws SQLException;
    void changeUsername(String oldUsername, String newUsername, String password) throws SQLException;
}
