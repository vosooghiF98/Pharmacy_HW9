package org.pharmacy.service.impl;

import org.pharmacy.Repository.AdminRepository;
import org.pharmacy.Repository.impl.AdminRepositoryImpl;
import org.pharmacy.entities.Admin;
import org.pharmacy.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    AdminRepository adminrepository = new AdminRepositoryImpl();

    @Override
    public boolean save(String username, String password) throws SQLException {
        if (adminrepository.loadByUsername(username)) {
            Admin admin = new Admin(username, password);
            adminrepository.save(admin);
            return true;
        }
        return false;
    }

    @Override
    public Admin load(String username, String password) throws SQLException {
        Admin admin = adminrepository.load(username, password);
        if (admin != null) {
            return admin;
        }
        return null;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        return true;

    }

    @Override
    public boolean changeUsername(String oldUsername, String newUsername, String password) {
        return true;
    }
}
