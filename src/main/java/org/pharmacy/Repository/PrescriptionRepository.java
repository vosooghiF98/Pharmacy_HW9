package org.pharmacy.Repository;

import org.pharmacy.entities.Drug;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;

import java.sql.SQLException;

public interface PrescriptionRepository {
    void save(Drug drug, Patient patient) throws SQLException;
    void editPrescription(String oldName, Drug newDrug, Patient patient) throws SQLException;
    void changeExistMode(boolean exist , String name) throws SQLException;
    void changeConfirmMode(boolean confirm ,String nationalCode) throws SQLException;
    void changePaymentMode(boolean pay ,String nationalCode) throws SQLException;
    Prescription loadBeforeConfirm(Patient patient) throws SQLException;
    Prescription loadAfterConfirm(Patient patient) throws SQLException;
    Prescription loadAll() throws SQLException;
    void remove(Patient patient) throws SQLException;
    void refresh();
}
