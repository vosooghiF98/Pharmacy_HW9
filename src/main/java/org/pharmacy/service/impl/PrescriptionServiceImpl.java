package org.pharmacy.service.impl;

import org.pharmacy.Repository.PrescriptionRepository;
import org.pharmacy.Repository.impl.PrescriptionRepositoryImpl;
import org.pharmacy.entities.Drug;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;
import org.pharmacy.service.PrescriptionService;

import java.sql.SQLException;

public class PrescriptionServiceImpl implements PrescriptionService {
    PrescriptionRepository prescriptionRepository = new PrescriptionRepositoryImpl();
    @Override
    public boolean save(String name, int quantity, Patient patient) throws SQLException {
        if (load(patient) == null){
            Drug drug = new Drug(name, quantity);
            prescriptionRepository.save(drug, patient);
            return true;
        }
        else if (prescriptionRepository.loadBeforeConfirm(patient).size() < 10 && !load(patient).contains(name)) {
            Drug drug = new Drug(name, quantity);
            prescriptionRepository.save(drug, patient);
            return true;
        }
        return false;
    }

    @Override
    public boolean editPrescription(String oldName,String name, int quantity, Patient patient) throws SQLException {
        Drug drug = new Drug(name,quantity);
        if (load(patient).contains(oldName)) {
            prescriptionRepository.editPrescription(oldName, drug, patient);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeMode(String mode, boolean change, String name) throws SQLException {
        if (loadAll().contains(name)) {
            if (mode.equalsIgnoreCase("exist")) {
                prescriptionRepository.changeExistMode(change, name);
            } else if (mode.equalsIgnoreCase("confirm")) {
                prescriptionRepository.changeConfirmMode(name);
            } else if (mode.equalsIgnoreCase("pay")) {
                prescriptionRepository.changePaymentMode(change, name);
            }
            return true;
        }
        return false;
    }
    @Override
    public boolean addPrice(String name, long price, String nationalCode) throws SQLException {
        Patient patient = new Patient(nationalCode);
        if (load(patient).contains(name)) {
            prescriptionRepository.addPrice(name, price, nationalCode);
            return true;
        }
        return false;
    }

    @Override
    public Prescription load(Patient patient) throws SQLException {
        Prescription before = prescriptionRepository.loadBeforeConfirm(patient);
        Prescription after = prescriptionRepository.loadAfterConfirm(patient);
        if (before.size() != 0){
            return before;
        }else if (after.size() != 0){
            return after;
        }else
            return null;
    }

    @Override
    public Prescription loadAll() throws SQLException {
        Prescription loadAll = prescriptionRepository.loadAll();
        if (loadAll.size() != 0){
            return loadAll;
        }else
            return null;
    }

    @Override
    public void remove(Patient patient) throws SQLException {
        prescriptionRepository.remove(patient);
    }
}
