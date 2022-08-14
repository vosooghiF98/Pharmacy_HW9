package org.pharmacy.service;

import org.pharmacy.Repository.PrescriptionRepository;
import org.pharmacy.Repository.impl.PrescriptionRepositoryImpl;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;

import java.sql.SQLException;

public interface PrescriptionService {
    public void save(String name, int quantity, Patient patient) throws SQLException;
    public void editPrescription(String name, int quantity, Patient patient) throws SQLException;
    public void changeMode(String mode, boolean change ,String name) throws SQLException;
    public Prescription load(Patient patient) throws SQLException;
    public Prescription loadAll() throws SQLException;
    public void remove(Patient patient) throws SQLException;




}
