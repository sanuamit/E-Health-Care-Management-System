package services;

import models.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class HospitalManagement {
    private Map<Integer, Patient> patients = new HashMap<>();
    private Map<Integer, Doctor> doctors = new HashMap<>();
    private Map<Integer, Appointment> appointments = new HashMap<>();

    private int nextPatientId = 1;
    private int nextDoctorId = 1;
    private int nextAppointmentId = 1;

    private final File patientFile = new File("data/patients.dat");
    private final File doctorFile = new File("data/doctors.dat");
    private final File appointmentFile = new File("data/appointments.dat");

    public HospitalManagement() {
        loadAll();
        patients.keySet().stream().max(Integer::compareTo).ifPresent(max -> nextPatientId = max + 1);
        doctors.keySet().stream().max(Integer::compareTo).ifPresent(max -> nextDoctorId = max + 1);
        appointments.keySet().stream().max(Integer::compareTo).ifPresent(max -> nextAppointmentId = max + 1);
    }

    // Patients
    public Patient addPatient(String name, int age, String phone, String address) {
        Patient p = new Patient(nextPatientId++, name, age, phone, address);
        patients.put(p.getId(), p);
        savePatients();
        return p;
    }

    public Patient getPatient(int id) { return patients.get(id); }
    public Collection<Patient> getAllPatients() { return patients.values(); }

    public boolean updatePatient(int id, String name, Integer age, String phone, String address) {
        Patient p = patients.get(id);
        if (p == null) return false;
        if (name != null) p.setName(name);
        if (age != null) p.setAge(age);
        if (phone != null) p.setPhone(phone);
        if (address != null) p.setAddress(address);
        savePatients();
        return true;
    }

    public boolean deletePatient(int id) {
        if (patients.remove(id) != null) {
            // also remove appointments for this patient
            appointments.values().removeIf(a -> a.getPatientId() == id);
            savePatients();
            saveAppointments();
            return true;
        }
        return false;
    }

    // Doctors
    public Doctor addDoctor(String name, String specialization, String phone) {
        Doctor d = new Doctor(nextDoctorId++, name, specialization, phone);
        doctors.put(d.getId(), d);
        saveDoctors();
        return d;
    }

    public Doctor getDoctor(int id) { return doctors.get(id); }
    public Collection<Doctor> getAllDoctors() { return doctors.values(); }

    public boolean updateDoctor(int id, String name, String specialization, String phone) {
        Doctor d = doctors.get(id);
        if (d == null) return false;
        if (name != null) d.setName(name);
        if (specialization != null) d.setSpecialization(specialization);
        if (phone != null) d.setPhone(phone);
        saveDoctors();
        return true;
    }

    public boolean deleteDoctor(int id) {
        if (doctors.remove(id) != null) {
            // remove appointments for this doctor
            appointments.values().removeIf(a -> a.getDoctorId() == id);
            saveDoctors();
            saveAppointments();
            return true;
        }
        return false;
    }

    // Appointments
    public Appointment bookAppointment(int patientId, int doctorId, LocalDateTime dateTime, String notes) {
        if (!patients.containsKey(patientId) || !doctors.containsKey(doctorId)) return null;
        Appointment a = new Appointment(nextAppointmentId++, patientId, doctorId, dateTime, notes);
        appointments.put(a.getId(), a);
        saveAppointments();
        return a;
    }

    public Collection<Appointment> getAllAppointments() { return appointments.values(); }

    public boolean cancelAppointment(int id) {
        if (appointments.remove(id) != null) {
            saveAppointments();
            return true;
        }
        return false;
    }

    // Persistence helpers
    @SuppressWarnings("unchecked")
    private <T> Map<Integer, T> readMapFromFile(File f) {
        if (!f.exists()) return new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = in.readObject();
            return (Map<Integer, T>) obj;
        } catch (Exception e) {
            System.out.println("Warning: failed to read file: " + f.getName() + " - starting fresh.");
            return new HashMap<>();
        }
    }

    private void writeMapToFile(File f, Map<?, ?> map) {
        try {
            File parent = f.getParentFile();
            if (parent != null) parent.mkdirs();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f))) {
                out.writeObject(map);
            }
        } catch (IOException e) {
            System.out.println("Error: failed to write file: " + f.getName() + " - " + e.getMessage());
        }
    }

    private void loadAll() {
        patients = readMapFromFile(patientFile);
        doctors = readMapFromFile(doctorFile);
        appointments = readMapFromFile(appointmentFile);
    }

    private void savePatients() { writeMapToFile(patientFile, patients); }
    private void saveDoctors()  { writeMapToFile(doctorFile, doctors); }
    private void saveAppointments() { writeMapToFile(appointmentFile, appointments); }
}
