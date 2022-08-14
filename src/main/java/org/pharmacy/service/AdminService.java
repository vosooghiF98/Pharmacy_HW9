package org.pharmacy.service;

import org.pharmacy.entities.Admin;

import java.sql.SQLException;

public interface AdminService {
    boolean save(String username, String password) throws SQLException;
    Admin load(String username, String password) throws SQLException;
    boolean changePassword(String oldPassword, String newPassword);
    boolean changeUsername(String oldUsername, String newUsername, String password);
}
