package PatientManagement.entity;
import utils.Registry;

public class Patient implements Registry.Identifiable<Integer>{
    private static int patientSequence = 1001; 
    private int patientId;
    private String name;
    private int age;
    private String phoneNo;
    private String email;

    public Patient(String name, int age, String phoneNo, String email){
        this.patientId = patientSequence;
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.email = email;

        patientSequence++;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public int getPatientId(){
        return this.patientId;
    }

    public String getEmail(){
        return this.email;
    }

    public String toString(){
        return "patientID: "+ this.patientId + "Name: " + this.name + "Age: " + this.age + "Phone No: "+ phoneNo; 
    }

    @Override
    public Integer key() {
        return patientId;
    }
}
