/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.patient.boundary;

import modules.patient.entity.Patient;
import utils.Context;
import utils.UI;

public class PatientInformationUI extends UI<Context> {
    private final boolean register;

    public PatientInformationUI(boolean register) {
        this.register= register;
    }

    private static boolean validateName(String name){
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))) return false;
        }
        return true;
    }

    @Override
    protected UI<Context> display() {
        println("===Patient Information===\n");

        if(register){
            String name = readString("Enter Name: ",input-> validateName(input) ? null: "No digits allowed!");
            int age = readInt("Enter Age: ", input-> input>0 ? null: "Age must be more than 0!");
            String phoneNo = readString("Enter Phone Number: ",noValidation());
            String email= readString("Enter Email Address", noValidation());

            context.patients.register(new Patient(context.patientCounter.get(), name, age, phoneNo, email));
            println("New patient is registered successfully!");

        }else{
            int patientId = readInt("Enter Patient ID: ",input -> context.patients.find(input) == null ? "Patient not existed": null);

            var patient = context.patients.find(patientId);
            String name = readString("Enter name: ", input -> input.isBlank()|| validateName(input) ? null: "No digits allowed!");
            if (!name.isBlank()){
                patient.setName(name);
            }

            int age = readInt("Enter Age: ",patient.getAge(), input-> input > 0 ? null : "Age must be more than 0!");
            if (age > 0){
                patient.setAge(age);
            }
            
            String phoneNo = readString("Enter Phone Number: ",noValidation());
            if(!phoneNo.isBlank()){
                patient.setPhoneNo(phoneNo);
            }

            String email = readString("Enter Email: ", noValidation());
            if (!email.isBlank()){
                patient.setEmail(email);
            }

            println("Information Updated Successfully!");
        }

        waitInput();
        return null;
    }
}
