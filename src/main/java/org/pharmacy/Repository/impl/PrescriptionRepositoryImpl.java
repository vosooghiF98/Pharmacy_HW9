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
    public void editPrescription(String oldName, Drug newDrug, Patient patient) throws SQLException {
        String query = """
                update prescription set name = ? , quantity = ? where patient_national_code = ? and name = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,newDrug.getName());
        preparedStatement.setInt(2,newDrug.getQuantity());
        preparedStatement.setString(3,patient.getNationalCode());
        preparedStatement.setString(4,oldName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void changeExistMode(boolean exist ,String name) throws SQLException {
        String query = """
                update prescription set dose_exist = ? where name = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1,exist);
        preparedStatement.setString(2,name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void changeConfirmMode(boolean confirm,String nationalCode) throws SQLException {
        String query = """
                update prescription set is_confirmed = ? where patient_national_code = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1,confirm);
        preparedStatement.setString(2,nationalCode);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void changePaymentMode(boolean pay, String nationalCode) throws SQLException {
        String query = """
                update prescription set is_paid = ? where patient_national_code = ?
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1,pay);
        preparedStatement.setString(2,nationalCode);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    @Override
    public void addPrice(String name, long price, String nationalCode) throws SQLException {
        String query = """
                update prescription set price = ? where name = ? and patient_national_code = ? and dose_exist = true
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setLong(1,price);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,nationalCode);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public Prescription loadBeforeConfirm(Patient patient) throws SQLException {
        String query = """
                select name,quantity,patient_national_code,is_confirmed from prescription where patient_national_code = ? and is_confirmed = false
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1, patient.getNationalCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        Prescription prescription = new Prescription();
        while (resultSet.next()){
            Drug drug = new Drug(resultSet.getString("name"),resultSet.getInt("quantity"));
            drug.setPatientNationalCode(resultSet.getString("patient_national_code"));
            drug.setConfirm(resultSet.getBoolean("is_confirmed"));
            prescription.add(drug);
        }
        preparedStatement.close();
        return prescription;
    }

    @Override
    public Prescription loadAfterConfirm(Patient patient) throws SQLException {
        String query = """
                select name,quantity,dose_exist,price,patient_national_code,is_confirmed,is_paid from prescription where patient_national_code = ? and is_confirmed = true and is_paid = false
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1, patient.getNationalCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        Prescription prescription = new Prescription();
        while (resultSet.next()){
            Drug drug = new Drug(resultSet.getString("name"),resultSet.getInt("quantity"));
            drug.setDoesExist(resultSet.getBoolean("dose_exist"));
            drug.setPrice(resultSet.getLong("price"));
            drug.setPatientNationalCode(resultSet.getString("patient_national_code"));
            drug.setConfirm(resultSet.getBoolean("is_confirmed"));
            drug.setPay(resultSet.getBoolean("is_paid"));
            drug.setTotalPrice();
            prescription.add(drug);
        }
        preparedStatement.close();
        return prescription;
    }

    @Override
    public Prescription loadAll() throws SQLException {
        String query = """
                select * from prescription where is_confirmed=false order by id
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Prescription prescription = new Prescription();
        while (resultSet.next()){
            Drug drug = new Drug(resultSet.getString("name"),resultSet.getInt("quantity"));
            drug.setPrice(resultSet.getLong("price"));
            drug.setPatientNationalCode(resultSet.getString("patient_national_code"));
            prescription.add(drug);
        }
        preparedStatement.close();
        return prescription;
    }

    @Override
    public void remove(Patient patient) throws SQLException {
        String query = """
                delete from prescription where patient_national_code = ? and is_paid = false
                """;
        PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
        preparedStatement.setString(1,patient.getNationalCode());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
