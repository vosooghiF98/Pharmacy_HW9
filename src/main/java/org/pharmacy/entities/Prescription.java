package org.pharmacy.entities;

import java.util.Arrays;
import java.util.Objects;

public class Prescription {
    private Drug[] drugs = new Drug[10];
    int index = 0;

    public void add(Drug prescription) {
        if (index > drugs.length - 1) {
            drugs = Arrays.copyOf(drugs, drugs.length * 2);
        }
        drugs[index] = prescription;
        index++;
    }

    public void remove(int id) {
        if (id < index) {
            drugs[id] = null;
        }
        if (index - id >= 0) System.arraycopy(drugs, id + 1, drugs, id, index - id);
    }

    public int size(){
        return index;
    }

    public boolean contains(String name){
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(name, drugs[i].getName())){
                return true;
            }
        }
        return false;
    }
    public String getName(int index){
        return drugs[index].getName();
    }
    public boolean getConfirm(){
        return drugs[0].isConfirm();
    }
    public boolean getPay(){
        return drugs[0].isPay();
    }
    public void prescriptionPrice(){
        long prescriptionPrice = 0;
        for (int i = 0; i < size(); i++) {
            prescriptionPrice += drugs[i].getTotalPrice();
        }
        System.out.println("The Total Amount Payable For This Prescription is : " + prescriptionPrice);
    }
    @Override
    public String toString() {
        if (size() != 0) {
            for (int i = 0; i < size(); i++) {
                System.out.println(drugs[i].toString());
            }
            return "";
        }else
            return null;
    }
}
