package org.pharmacy.service.impl;

import org.pharmacy.repository.PatientRepository;
import org.pharmacy.repository.impl.PatientRepositoryImpl;
import org.pharmacy.entities.Patient;
import org.pharmacy.service.PatientService;

import java.sql.SQLException;

public class PatientServiceImpl implements PatientService {
    PatientRepository patientRepository = new PatientRepositoryImpl();
    @Override
    public boolean save(String nationalCode) throws SQLException {
        if (patientRepository.load(nationalCode) == null){
            patientRepository.save(nationalCode);
            return true;
        }else
            return false;
    }

    @Override
    public Patient load(String nationalCode) throws SQLException {
        if (patientRepository.load(nationalCode) != null){
            return patientRepository.load(nationalCode);
        }else
            return null;
    }

    @Override
    public boolean edit(String oldNationalCode, String newNationalCode) throws SQLException {
        if (load(oldNationalCode) != null){
            patientRepository.edit(oldNationalCode,newNationalCode);
            return true;
        }else
            return false;
    }
}
