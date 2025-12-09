import services.HospitalManagement;
import models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final HospitalManagement hm = new HospitalManagement();
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        System.out.println("Welcome to E-Health Care Management System");
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:\n1. Manage Patients\n2. Manage Doctors\n3. Appointments\n4. View All Appointments\n5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": patientsMenu(); break;
                case "2": doctorsMenu(); break;
                case "3": appointmentsMenu(); break;
                case "4": viewAppointments(); break;
                case "5": running = false; System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    // Patients menu
    private static void patientsMenu() {
        System.out.println("\nPatients Menu:\n1. Add Patient\n2. List Patients\n3. Update Patient\n4. Delete Patient\n5. Back");
        System.out.print("Choose: ");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1": addPatient(); break;
            case "2": listPatients(); break;
            case "3": updatePatient(); break;
            case "4": deletePatient(); break;
            case "5": return;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void addPatient() {
        try {
            System.out.print("Enter name: "); String name = nonEmpty(sc.nextLine());
            System.out.print("Enter age: "); int age = parseInt(sc.nextLine(), "age");
            System.out.print("Enter phone: "); String phone = nonEmpty(sc.nextLine());
            System.out.print("Enter address: "); String address = nonEmpty(sc.nextLine());
            Patient p = hm.addPatient(name, age, phone, address);
            System.out.println("Added: " + p.getInfo());
        } catch (Exception e) { System.out.println("Failed to add patient: " + e.getMessage()); }
    }

    private static void listPatients() {
        System.out.println("\nPatients:");
        hm.getAllPatients().stream().sorted((a,b)->Integer.compare(a.getId(), b.getId()))
            .forEach(p -> System.out.println(p.getInfo()));
    }

    private static void updatePatient() {
        try {
            System.out.print("Enter patient ID to update: "); int id = parseInt(sc.nextLine(), "patient id");
            Patient p = hm.getPatient(id);
            if (p == null) { System.out.println("No patient with ID " + id); return; }
            System.out.println("Leave blank to keep current value.");
            System.out.print("Name (" + p.getName() + "): "); String name = sc.nextLine().trim(); if (name.isEmpty()) name = null;
            System.out.print("Age (" + p.getAge() + "): "); String ageStr = sc.nextLine().trim(); Integer age = ageStr.isEmpty() ? null : Integer.parseInt(ageStr);
            System.out.print("Phone (" + p.getPhone() + "): "); String phone = sc.nextLine().trim(); if (phone.isEmpty()) phone = null;
            System.out.print("Address (" + p.getAddress() + "): "); String addr = sc.nextLine().trim(); if (addr.isEmpty()) addr = null;
            boolean ok = hm.updatePatient(id, name, age, phone, addr);
            System.out.println(ok ? "Updated." : "Update failed.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void deletePatient() {
        try {
            System.out.print("Enter patient ID to delete: "); int id = parseInt(sc.nextLine(), "patient id");
            boolean ok = hm.deletePatient(id);
            System.out.println(ok ? "Deleted patient (and related appointments)." : "No patient with ID " + id);
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    // Doctors menu
    private static void doctorsMenu() {
        System.out.println("\nDoctors Menu:\n1. Add Doctor\n2. List Doctors\n3. Update Doctor\n4. Delete Doctor\n5. Back");
        System.out.print("Choose: ");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1": addDoctor(); break;
            case "2": listDoctors(); break;
            case "3": updateDoctor(); break;
            case "4": deleteDoctor(); break;
            case "5": return;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void addDoctor() {
        try {
            System.out.print("Enter name: "); String name = nonEmpty(sc.nextLine());
            System.out.print("Enter specialization: "); String spec = nonEmpty(sc.nextLine());
            System.out.print("Enter phone: "); String phone = nonEmpty(sc.nextLine());
            Doctor d = hm.addDoctor(name, spec, phone);
            System.out.println("Added: " + d.getInfo());
        } catch (Exception e) { System.out.println("Failed to add doctor: " + e.getMessage()); }
    }

    private static void listDoctors() {
        System.out.println("\nDoctors:");
        hm.getAllDoctors().stream().sorted((a,b)->Integer.compare(a.getId(), b.getId()))
            .forEach(d -> System.out.println(d.getInfo()));
    }

    private static void updateDoctor() {
        try {
            System.out.print("Enter doctor ID to update: "); int id = parseInt(sc.nextLine(), "doctor id");
            Doctor d = hm.getDoctor(id);
            if (d == null) { System.out.println("No doctor with ID " + id); return; }
            System.out.println("Leave blank to keep current value.");
            System.out.print("Name (" + d.getName() + "): "); String name = sc.nextLine().trim(); if (name.isEmpty()) name = null;
            System.out.print("Specialization (" + d.getSpecialization() + "): "); String spec = sc.nextLine().trim(); if (spec.isEmpty()) spec = null;
            System.out.print("Phone (" + d.getPhone() + "): "); String phone = sc.nextLine().trim(); if (phone.isEmpty()) phone = null;
            boolean ok = hm.updateDoctor(id, name, spec, phone);
            System.out.println(ok ? "Updated." : "Update failed.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void deleteDoctor() {
        try {
            System.out.print("Enter doctor ID to delete: "); int id = parseInt(sc.nextLine(), "doctor id");
            boolean ok = hm.deleteDoctor(id);
            System.out.println(ok ? "Deleted doctor (and related appointments)." : "No doctor with ID " + id);
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    // Appointments menu
    private static void appointmentsMenu() {
        System.out.println("\nAppointments Menu:\n1. Book Appointment\n2. Cancel Appointment\n3. Back");
        System.out.print("Choose: ");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1": bookAppointment(); break;
            case "2": cancelAppointment(); break;
            case "3": return;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void bookAppointment() {
        try {
            System.out.print("Enter patient ID: "); int pid = parseInt(sc.nextLine(), "patient id");
            System.out.print("Enter doctor ID: "); int did = parseInt(sc.nextLine(), "doctor id");
            System.out.print("Enter datetime (YYYY-MM-DD HH:MM): "); String dt = sc.nextLine().trim();
            LocalDateTime ldt = LocalDateTime.parse(dt, dtf);
            System.out.print("Enter notes: "); String notes = sc.nextLine().trim();
            Appointment ap = hm.bookAppointment(pid, did, ldt, notes);
            if (ap == null) System.out.println("Failed: invalid patient or doctor ID.");
            else System.out.println("Booked: " + ap);
        } catch (Exception e) { System.out.println("Error booking appointment: " + e.getMessage()); }
    }

    private static void cancelAppointment() {
        try {
            System.out.print("Enter appointment ID to cancel: "); int id = parseInt(sc.nextLine(), "appointment id");
            boolean ok = hm.cancelAppointment(id);
            System.out.println(ok ? "Appointment canceled." : "No appointment with ID " + id);
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void viewAppointments() {
        System.out.println("\nAppointments:");
        hm.getAllAppointments().stream().sorted((a,b)->Integer.compare(a.getId(), b.getId()))
            .forEach(a -> System.out.println(a));
    }

    // Helpers
    private static int parseInt(String s, String name) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid " + name + " (must be integer).");
        }
    }

    private static String nonEmpty(String s) {
        if (s == null) throw new IllegalArgumentException("Input required.");
        s = s.trim();
        if (s.isEmpty()) throw new IllegalArgumentException("Input required.");
        return s;
    }
}
