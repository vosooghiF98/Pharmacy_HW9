package org.pharmacy.entities;

import org.pharmacy.entities.Drug;

import java.util.Arrays;

public class Prescription {
    private Drug[] prescriptions = new Drug[10];
    int index = 0;

    public void add(Drug prescription) {
        if (index > prescriptions.length - 1) {
            prescriptions = Arrays.copyOf(prescriptions, prescriptions.length * 2);
        }
        prescriptions[index] = prescription;
        index++;
    }

    public void remove(int id) {
        if (id < index) {
            prescriptions[id] = null;
        }
        if (index - id >= 0) System.arraycopy(prescriptions, id + 1, prescriptions, id, index - id);
    }

    public int size(){
        return index;
    }

    public boolean contains(String name){
        for (int i = 0; i < size(); i++) {
            if (name == prescriptions[i].getName()){
                return true;
            }
        }
        return false;
    }


}
