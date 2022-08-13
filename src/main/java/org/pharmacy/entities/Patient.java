package org.pharmacy.entities;

public class Patient {
    private String nationalCode;

    public Patient(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
