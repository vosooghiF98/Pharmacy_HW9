package org.pharmacy.service;

import org.pharmacy.entities.Patient;

import java.sql.SQLException;

public interface PatientService {
    boolean save(String nationalCode) throws SQLException;
    Patient load(String nationalCode) throws SQLException;
    boolean edit(String oldNationalCode, String newNationalCode) throws SQLException;
}
