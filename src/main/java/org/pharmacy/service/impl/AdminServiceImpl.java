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
    public boolean changePassword(Admin admin, String newPassword) throws SQLException {
        if (load(admin.getUsername(), admin.getPassword()) != null){
            adminrepository.changePassword(admin.getUsername(), admin.getPassword(), newPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeUsername(Admin admin, String newUsername) throws SQLException {
        if (load(admin.getUsername(), admin.getPassword()) != null){
            adminrepository.changeUsername(admin.getUsername(), newUsername, admin.getPassword());
            return true;
        }
        return false;
    }
}
