package models;

public class Patient extends User {
    private static final long serialVersionUID = 1L;
    private int age;
    private String address;

    public Patient(int id, String name, int age, String phone, String address) {
        super(id, name, phone);
        this.age = age;
        this.address = address;
    }

    public int getAge() { return age; }
    public String getAddress() { return address; }

    public void setAge(int age) { this.age = age; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String getInfo() {
        return "Patient[" + id + "] " + name + ", Age: " + age + ", Phone: " + phone + ", Addr: " + address;
    }
}
