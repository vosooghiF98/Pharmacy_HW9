package org.pharmacy.repository;

import org.pharmacy.entities.Admin;

import java.sql.SQLException;

public interface AdminRepository {
    void save(Admin admin) throws SQLException;
    Admin load(String username, String password) throws SQLException;
    boolean loadByUsername(String username) throws SQLException;
    void changePassword(String username, String oldPassword, String newPassword) throws SQLException;
    void changeUsername(String oldUsername, String newUsername, String password) throws SQLException;
}
