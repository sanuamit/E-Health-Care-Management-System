package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int patientId;
    private int doctorId;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(int id, int patientId, int doctorId, LocalDateTime dateTime, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getNotes() { return notes; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Appointment[" + id + "] PatientID:" + patientId + " DoctorID:" + doctorId + " @" + dateTime.format(f) + " Notes:" + notes;
    }
}
