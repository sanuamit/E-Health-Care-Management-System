package models;

public class Doctor extends User {
    private static final long serialVersionUID = 1L;
    private String specialization;

    public Doctor(int id, String name, String specialization, String phone) {
        super(id, name, phone);
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public String getInfo() {
        return "Dr. " + name + " (ID:" + id + ") - " + specialization + " - Phone: " + phone;
    }
}
