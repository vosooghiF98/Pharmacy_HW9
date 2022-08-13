package org.pharmacy.entities;

public class Prescription {
    private int id;
    private String name;
    private int quantity;
    private long price;
    private boolean doesExist;
    private boolean isConfirmed;
    private boolean isPaid;

    public Prescription(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        price = 0;
        isConfirmed = false;
        isPaid = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
