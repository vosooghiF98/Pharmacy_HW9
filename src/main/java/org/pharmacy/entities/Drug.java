package org.pharmacy.entities;

import java.util.Objects;

public class Drug {
    private String name;
    private int quantity;
    private long price;
    private boolean doesExist;
    private boolean isConfirm;
    private boolean isPay;
    private long totalPrice;
    private String patientNationalCode;
    public Drug(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isDoesExist() {
        return doesExist;
    }

    public void setDoesExist(boolean doesExist) {
        this.doesExist = doesExist;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        totalPrice = price*quantity;
    }

    public String getPatientNationalCode() {
        return patientNationalCode;
    }

    public void setPatientNationalCode(String patientNationalCode) {
        this.patientNationalCode = patientNationalCode;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", doesExist=" + doesExist +
                ", totalPrice=" + totalPrice +
                ", patient's national code=" + patientNationalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug that = (Drug) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
