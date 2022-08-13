package org.pharmacy.Repository.impl;

import org.pharmacy.Repository.PrescriptionRepository;
import org.pharmacy.config.DBConfig;
import org.pharmacy.entities.Prescription;
import org.pharmacy.util.list.PrescriptionList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionRepositoryImpl implements PrescriptionRepository {
    @Override
    public void save(Prescription prescription,int patientId) throws SQLException {
        String query = """
                insert into prescription (name, quantity, is_confirmed, is_paid, patient_id)
                values (?,?,?,?,?)
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,prescription.getName());
        preparedStatement.setInt(2,prescription.getQuantity());
        preparedStatement.setBoolean(3,prescription.isConfirmed());
        preparedStatement.setBoolean(4,prescription.isPaid());
        preparedStatement.setInt(5,patientId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void editPatient(Prescription prescription) throws SQLException {
        String query = """
                update prescription set name = ? , quantity = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,prescription.getName());
        preparedStatement.setInt(2,prescription.getQuantity());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void editAdmin(boolean exist ,boolean confirm ,boolean pay) throws SQLException {
        String query = """
                update prescription set dose_exist = ? , is_confirmed = ? , is_paid = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1,exist);
        preparedStatement.setBoolean(2,confirm);
        preparedStatement.setBoolean(3,pay);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public PrescriptionList load(int patientId) throws SQLException {
        String query = """
                select name,quantity,dose_exist,is_confirmed,is_paid from prescription where patient_id = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,patientId);
        ResultSet resultSet = preparedStatement.executeQuery();
        PrescriptionList prescriptionList = new PrescriptionList();
        while (resultSet.next()){
            Prescription prescription = new Prescription(resultSet.getString("name"),resultSet.getInt("quantity"));
            prescription.setDoesExist(resultSet.getBoolean("dose_exist"));
            prescription.setConfirmed(resultSet.getBoolean("is_confirmed"));
            prescription.setPaid(resultSet.getBoolean("is_paid"));
            prescriptionList.add(prescription);
        }
        preparedStatement.close();
        return prescriptionList;
    }

    @Override
    public PrescriptionList loadAll() {
        return null;
    }

    @Override
    public void remove(int patientId) {

    }

    @Override
    public void refresh() {

    }
}
