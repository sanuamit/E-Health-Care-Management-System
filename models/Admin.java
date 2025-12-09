package models;

public class Admin extends User {
    private static final long serialVersionUID = 1L;

    public Admin(int id, String name, String phone) {
        super(id, name, phone);
    }

    @Override
    public String getInfo() {
        return "Admin[" + id + "] " + name + " (" + phone + ")";
    }
}
