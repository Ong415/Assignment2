/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.consultation.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hoche
 */
public class Appointment {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private LocalDateTime dateTime;
    private String status;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Appointment(String appointmentID, String patientID, String doctorID,LocalDateTime dateTime, String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.dateTime = dateTime;
        this.status = status;
        
        if(!isValidAppointmentID(appointmentID)){
           throw new IllegalArgumentException("Invalid Appointment ID. Must be like A001."); 
        }
        
        if(!isValidPatientID(patientID)){
           throw new IllegalArgumentException("Invalid Patient ID. Must be like P001."); 
        }
        
        if(!isValidDoctorID(doctorID)){
           throw new IllegalArgumentException("Invalid Doctor ID. Must be like D001."); 
        }
    }
    
    
    //Validate ID format 
    public static boolean isValidPatientID(String id){
        return id != null && id.matches("^P\\d{3}$");
    }
    
    public static boolean isValidAppointmentID(String id){
        return id != null && id.matches("^P\\d{3}$");
    }
    
    public static boolean isValidDoctorID(String id){
        return id != null && id.matches("^D\\d{3}$");
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return String.format("%s | P:%s | D:%s | %s | %s",
                appointmentID, patientID, doctorID,
                dateTime.format(FORMATTER), status);
    }
}


/* Validate ID format 
    public boolean validateId(String id, String type) {
        if (id == null || id.isEmpty()) return false;

        switch (type.toLowerCase()) {
            case "patient":
                return id.matches("^P\\d{3}$");
            case "doctor":
                return id.matches("^D\\d{3}$");
            case "consultation":
                return id.matches("^C\\d{3}$");
            case "appointment":
                return id.matches("^A\\d{3}$");
            default:
                return false;
        }
    }*/
