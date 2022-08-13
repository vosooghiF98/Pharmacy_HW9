package org.pharmacy.Repository.impl;

import org.pharmacy.Repository.PrescriptionRepository;
import org.pharmacy.config.DBConfig;
import org.pharmacy.entities.Drug;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionRepositoryImpl implements PrescriptionRepository {
    @Override
    public void save(Drug drug, Patient patient) throws SQLException {
        String query = """
                insert into prescription (name, quantity, is_confirmed, is_paid, patient_national_code)
                values (?,?,false,false,?)
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,drug.getName());
        preparedStatement.setInt(2,drug.getQuantity());
        preparedStatement.setString(3, patient.getNationalCode());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void editPatient(Drug drug , Patient patient) throws SQLException {
        String query = """
                update prescription set name = ? , quantity = ? where patient_national_code = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,drug.getName());
        preparedStatement.setInt(2,drug.getQuantity());
        preparedStatement.setString(3,patient.getNationalCode());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void changeExistMode(boolean exist ,String name) throws SQLException {
        String query = """
                update prescription set dose_exist = ? where name = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1,exist);
        preparedStatement.setString(2,name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void changeConfirmMode(boolean confirm, String nationalCode) throws SQLException {
        String query = """
                update prescription set is_confirmed = true where patient_national_code = ?;
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,nationalCode);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public Prescription loadForEdit(Patient patient) throws SQLException {
        String query = """
                select name,quantity from prescription where patient_national_code = ? and is_confirmed = false
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1, patient.getNationalCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        Prescription prescription = new Prescription();
        while (resultSet.next()){
            Drug drug = new Drug(resultSet.getString("name"),resultSet.getInt("quantity"));
            prescription.add(drug);
        }
        preparedStatement.close();
        return prescription;
    }

    @Override
    public Prescription loadAfterConfirm(Patient patient) throws SQLException {
        String query = """
                select name,quantity,dose_exist,price from prescription where patient_national_code = ? and is_confirmed = true and is_paid = false
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1, patient.getNationalCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        Prescription prescription = new Prescription();
        while (resultSet.next()){
            Drug drug = new Drug(resultSet.getString("name"),resultSet.getInt("quantity"));
            drug.setDoesExist(resultSet.getBoolean("does_exist"));
            drug.setPrice(resultSet.getLong("price"));
            drug.setQuantity(resultSet.getInt("quantity"));
            drug.setTotalPrice();
            prescription.add(drug);
        }
        preparedStatement.close();
        return prescription;
    }

    @Override
    public Prescription loadAll() throws SQLException {
        String query = """
                select * from prescription where is_confirmed = false order by patient_national_code
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Prescription prescription = new Prescription();
        while (resultSet.next()){
            Drug drug = new Drug(resultSet.getString("name"),resultSet.getInt("quantity"));
            drug.setQuantity(resultSet.getInt("quantity"));
            drug.setPrice(resultSet.getLong("price"));

            prescription.add(drug);
        }
        preparedStatement.close();
        return prescription;
    }

    @Override
    public void remove(Patient patient) throws SQLException {
        String query = """
                delete from prescription where patient_national_code = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,patient.getNationalCode());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void refresh() {

    }
}
