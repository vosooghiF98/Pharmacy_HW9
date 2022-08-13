package org.pharmacy.util.list;

import org.pharmacy.entities.Prescription;

import java.util.Arrays;

public class PrescriptionList {
    private Prescription[] prescriptions = new Prescription[1000];
    int index = 0;

    public void add(Prescription prescription) {
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
}
