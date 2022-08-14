package org.pharmacy.service;

import org.pharmacy.Repository.PrescriptionRepository;
import org.pharmacy.Repository.impl.PrescriptionRepositoryImpl;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;

import java.sql.SQLException;

public interface PrescriptionService {
    boolean save(String name, int quantity, Patient patient) throws SQLException;
    boolean editPrescription(String oldName ,String name, int quantity, Patient patient) throws SQLException;
    boolean changeMode(String mode, boolean change ,String name) throws SQLException;
    Prescription load(Patient patient) throws SQLException;
    Prescription loadAll() throws SQLException;
    void remove(Patient patient) throws SQLException;




}
