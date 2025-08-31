package modules.patient.entity;

public class Patient {
    private final int id;
    private String name;
    private int age;
    private String phoneNo;
    private String email;

    public Patient(int id, String name, int age, String phoneNo, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "patientID: " + id + " |Name: " + name + " |Age: " + age + " |Phone No: " + phoneNo + " |Email : " + email;
    }
}
