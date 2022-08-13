package org.pharmacy.entities;

public class Patient {
    private int id;
    private String nationalCode;

    public Patient(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
