package org.pharmacy;

import org.pharmacy.check.Check;
import org.pharmacy.entities.Admin;
import org.pharmacy.entities.Patient;
import org.pharmacy.entities.Prescription;
import org.pharmacy.service.AdminService;
import org.pharmacy.service.PatientService;
import org.pharmacy.service.PrescriptionService;
import org.pharmacy.service.impl.AdminServiceImpl;
import org.pharmacy.service.impl.PatientServiceImpl;
import org.pharmacy.service.impl.PrescriptionServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        AdminService adminService = new AdminServiceImpl();
        PatientService patientService = new PatientServiceImpl();
        PrescriptionService prescriptionService = new PrescriptionServiceImpl();
        Patient patient = null;
        Admin admin = null;
        System.out.println("Menu :");
        System.out.println("Patient : 1");
        System.out.println("Admin : 2");
        System.out.println("Exit : 3");
        System.out.print("Enter Your Function : ");
        int button = Check.check(1,3,input);
        if (button == 1) {
            while (true) {
                System.out.println("Patient Menu : ");
                System.out.println("Sign Up : 1");
                System.out.println("Sign In : 2");
                System.out.println("Exit : 3");
                System.out.print("Enter Your Function : ");
                int button1 = Check.check(1,3,input);
                if (button1 == 1) {
                    String nationalCode = Check.checkNationalCode(input);
                    if (patientService.save(nationalCode)) {
                        System.out.println("Sing Up Successfully.");
                        patient = patientService.load(nationalCode);
                        System.out.println("Welcome");
                        break;
                    } else {
                        System.out.println("This National Code is Exist!");
                    }
                }
                if (button1 == 2) {
                    String nationalCode = Check.checkNationalCode(input);
                    if (patientService.load(nationalCode) != null) {
                        patient = patientService.load(nationalCode);
                        System.out.println("Welcome");
                        break;
                    } else {
                        System.out.println("This National Code is Not Exist!");
                        System.out.println("Please Sign Up First!");
                    }
                }
                if (button1 == 3) {
                    button = 3;
                    break;
                }
            }
            if (button != 3) {
                while (true) {
                    System.out.println("Menu :");
                    System.out.println("Add Prescription : 1");
                    System.out.println("Remove Prescription : 2");
                    System.out.println("Edit  Prescription : 3");
                    System.out.println("Show Prescription : 4");
                    System.out.println("Exit : 5");
                    System.out.print("Enter Your Function : ");
                    int button2 = Check.check(1,5,input);
                    if (button2 == 1) {
                        Prescription prescription = prescriptionService.load(patient);
                        if (prescription == null || !prescription.getConfirm()) {
                            System.out.print("Enter Drug's Name : ");
                            String name = input.next();
                            System.out.print("Enter Drug,s Quantity : ");
                            int quantity = Check.checkQuantity(input);
                            if (prescriptionService.save(name, quantity, patient)) {
                                System.out.println("Drug is Add to Your Prescription");
                            } else {
                                System.out.println("Your Drug's Name is Exist OR Your Prescription is Full!");
                            }
                        }else {
                            System.out.println("Pay OR Remove Your Last Prescription!");
                        }
                    }
                    if (button2 == 2) {
                        if (prescriptionService.load(patient) != null) {
                            System.out.print("Are You Sure to Remove Your Prescription ? (Y/N) : ");
                            boolean yn =Check.checkYN(input);
                            if (yn) {
                                prescriptionService.remove(patient);
                                System.out.println("Your Prescription Removed.");
                            }else {
                                System.out.println("Your Prescription Not Removed");
                            }
                        }else {
                            System.out.println("Nothing to Remove!");
                        }
                    }
                    if (button2 == 3){
                        Prescription prescription = prescriptionService.load(patient);
                        if (prescription != null && !prescription.getConfirm() && !prescription.getPay()) {
                            System.out.print("Enter Drug's Name : ");
                            String name = input.next();
                            if (prescription.contains(name)) {
                                System.out.print("Enter New Drug's Name : ");
                                String newName = input.next();
                                System.out.print("Enter New Drug,s Quantity : ");
                                int newQuantity = Check.checkQuantity(input);
                                prescriptionService.editPrescription(name, newName, newQuantity, patient);
                            }else {
                                System.out.println("This Name is Not Exist In Your Prescription!");
                            }
                        }else {
                            System.out.println("Nothing to Edit!");
                        }
                    }
                    if (button2 == 4){
                        Prescription prescription;
                        prescription = prescriptionService.load(patient);
                        if (prescription != null) {
                            System.out.println(prescription);
                            prescription.prescriptionPrice();
                        }else {
                            System.out.println("Nothing to Show!");
                        }
                    }
                    if (button2 == 5){
                        button = 3;
                        break;
                    }

                }
            }

        }
        if (button == 2){
            while (true) {
                System.out.println("Admin Menu : ");
                System.out.println("Sign Up : 1");
                System.out.println("Sign In : 2");
                System.out.println("Exit : 3");
                System.out.print("Enter Your Function : ");
                int button3 = Check.check(1,3,input);
                if (button3 == 1) {
                    System.out.print("Enter username : ");
                    String username = input.next();
                    System.out.print("Enter password : ");
                    String password = input.next();
                    if (adminService.save(username,password)) {
                        System.out.println("Sing Up Successfully.");
                        admin = adminService.load(username,password);
                        System.out.println("Welcome");
                        break;
                    } else {
                        System.out.println("This Admin is Exist!");
                    }
                }
                if (button3 == 2) {
                    System.out.print("Enter Your username : ");
                    String username = input.next();
                    System.out.print("Enter Your password : ");
                    String password = input.next();
                    if (adminService.load(username,password) != null) {
                        admin = adminService.load(username,password);
                        System.out.println("Welcome");
                        break;
                    } else {
                        System.out.println("This Admin is Not Exist!");
                        System.out.println("Please Sign Up First!");
                    }
                }
                if (button3 == 3) {
                    button = 3;
                    break;
                }
            }
            if (button != 3) {
                while (true) {
                    System.out.println("Menu :");
                    System.out.println("See All The Prescriptions : 1");
                    System.out.println("Confirm The Prescription : 2");
                    System.out.println("Accept Patient Payment : 3");
                    System.out.println("Exit : 4");
                    System.out.print("Enter Your Function : ");
                    int button4 = Check.check(1,4,input);
                    if (button4 == 1) {
                        Prescription prescription = prescriptionService.loadAll();
                        if (prescription != null){
                            System.out.println(prescription);
                        }else {
                            System.out.println("Nothing to Show!");
                        }
                    }
                    if (button4 == 2) {
                        String nationalCode = Check.checkNationalCode(input);
                        patient = patientService.load(nationalCode);
                        if (patient != null) {
                            if (!prescriptionService.load(patient).getConfirm()) {
                                System.out.print("Are You Sure to Confirm " + nationalCode + " ?(Y/N): ");
                                boolean yn = Check.checkYN(input);
                                prescriptionService.changeMode("confirm", yn, nationalCode);
                                if (yn) {
                                    System.out.println(nationalCode + " Is Confirmed.");
                                    Prescription prescription = prescriptionService.load(patient);
                                    String drugName;
                                    long price;
                                    if (prescription.size() > 0) {
                                        for (int i = 0; i < prescription.size(); i++) {
                                            drugName = prescription.getName(i);
                                            System.out.print("Does Exist " + drugName + " ?(Y/N): ");
                                            yn = Check.checkYN(input);
                                            prescriptionService.changeMode("exist", yn, drugName);
                                            if (yn) {
                                                System.out.print(drugName + " Price Is : ");
                                                price = Check.checkPrice(input);
                                                prescriptionService.addPrice(drugName, price, nationalCode);
                                            }
                                        }
                                    } else {
                                        System.out.println("This Patient Don't Have any Prescription!");
                                    }
                                } else {
                                    System.out.println(nationalCode + " Is Not Confirmed.");
                                }
                            }else {
                                System.out.println(nationalCode + " Is Confirmed later");
                            }
                        }else {
                            System.out.println("This Patient Is Not Exist!");
                        }
                    }
                    if (button4 == 3){
                        String nationalCode = Check.checkNationalCode(input);
                        if (patientService.load(nationalCode) != null) {
                            System.out.print("Are You Sure to Accept Payment for " + nationalCode + " ?(Y/N): ");
                            boolean yn = Check.checkYN(input);
                            prescriptionService.changeMode("pay", yn, nationalCode);
                            if (yn) {
                                System.out.println(nationalCode + " Payment Accepted.");
                            }else {
                                System.out.println(nationalCode + " Payment Not Accepted.");
                            }
                        }else {
                            System.out.println("This Patient Is Not Exist!");
                        }
                    }
                    if (button4 == 4){
                        button = 3;
                        break;
                    }

                }
            }
        }
    }
}