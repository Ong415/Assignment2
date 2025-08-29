package PatientMangement.entity;

public class Patient {
    private static int patientSequence = 1001; 
    private int patientID;
    private String name;
    private int age;
    private String phoneNo;

    public Patient(String name, int age, String phoneNo){
        this.patientID = patientSequence;
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;

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

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public int getPatientID(){
        return this.patientID;
    }

    public String ToString(){
        return "patientID: "+ this.patientID + "Name: " + this.name + "Age: " + this.age + "Phone No: "+ phoneNo; 
    }
}
