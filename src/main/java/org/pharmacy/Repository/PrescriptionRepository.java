package org.pharmacy.Repository;

import org.pharmacy.entities.Prescription;
import org.pharmacy.util.list.PrescriptionList;

import java.sql.SQLException;

public interface PrescriptionRepository {
    void save(Prescription prescription,int id) throws SQLException;
    void editPatient(Prescription prescription) throws SQLException;
    void editAdmin(boolean exist ,boolean confirm ,boolean pay) throws SQLException;
    PrescriptionList load(int patientId) throws SQLException;
    PrescriptionList loadAll();
    void remove(int patientId);
    void refresh();
}
