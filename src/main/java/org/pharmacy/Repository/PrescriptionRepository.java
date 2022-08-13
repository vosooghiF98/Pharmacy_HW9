package org.pharmacy.Repository;

import org.pharmacy.entities.Drug;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;

import java.sql.SQLException;

public interface PrescriptionRepository {
    void save(Drug prescription, Patient patient) throws SQLException;
    void editPatient(Drug prescription , Patient patient) throws SQLException;
    void changeExistMode(boolean exist ,String name) throws SQLException;
    void changeConfirmMode(boolean confirm ,String nationalCode) throws SQLException;
    Prescription loadForEdit(Patient patient) throws SQLException;
    Prescription loadAfterConfirm(Patient patient) throws SQLException;
    Prescription loadAll() throws SQLException;
    void remove(Patient patient) throws SQLException;
    void refresh();
}
