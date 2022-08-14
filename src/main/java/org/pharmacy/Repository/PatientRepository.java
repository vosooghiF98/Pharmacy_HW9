package org.pharmacy.Repository;

import org.pharmacy.entities.Patient;

import java.sql.SQLException;

public interface PatientRepository {
    void save(String nationalCode) throws SQLException;
    Patient load (String nationalCode) throws SQLException;
    void edit(String oldNationalCode, String newNationalCode) throws SQLException;
}
