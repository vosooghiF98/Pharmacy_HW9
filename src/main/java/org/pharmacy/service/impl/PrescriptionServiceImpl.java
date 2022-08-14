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
    public void save(String name, int quantity, Patient patient) throws SQLException {
        while (prescriptionRepository.loadBeforeConfirm(patient).size() <= 10) {
            Drug drug = new Drug(name, quantity);
            prescriptionRepository.save(drug, patient);
        }
    }

    @Override
    public void editPrescription(String name, int quantity, Patient patient) throws SQLException {
        Drug drug = new Drug(name,quantity);
        prescriptionRepository.editePrescription(drug,patient);
    }

    @Override
    public void changeMode(String mode, boolean change, String name) throws SQLException {
        if (mode.equalsIgnoreCase("exist")){
            prescriptionRepository.changeExistMode(change,name);
        }else if (mode.equalsIgnoreCase("confirm")){
            prescriptionRepository.changeConfirmMode(change,name);
        }else if (mode.equalsIgnoreCase("pay")){
            prescriptionRepository.changePaymentMode(change,name);
        }
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
