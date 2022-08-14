package org.pharmacy.Repository.impl;

import org.pharmacy.Repository.AdminRepository;
import org.pharmacy.config.DBConfig;
import org.pharmacy.entities.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public void save(String username, String password) throws SQLException {
        String query = """
                insert into admin (username, password)
                values (?,?)
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public Admin load(String username, String password) throws SQLException {
        String query = """
                select * from admin where username = ? and password = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new Admin(username,password);
        }
        return null;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) throws SQLException {
        String query = """
                update admin set password = ? where password = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,newPassword);
        preparedStatement.setString(2,oldPassword);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void changeUsername(String oldUsername, String newUsername, String password) throws SQLException {
        String query = """
                update admin set username = ? where username = ? and password = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,newUsername);
        preparedStatement.setString(2,oldUsername);
        preparedStatement.setString(3,password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}