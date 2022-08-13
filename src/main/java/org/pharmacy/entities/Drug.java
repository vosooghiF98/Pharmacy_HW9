package org.pharmacy.entities;

import java.util.Objects;

public class Drug {
    private String name;
    private int quantity;
    private long price;
    private boolean doesExist;
    private long totalPrice;
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

    @Override
    public String toString() {
        return "Prescription{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", doesExist=" + doesExist +
                ", totalPrice=" + totalPrice +
                '}';
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