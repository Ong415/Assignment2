/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.consultation.entity;
import java.time.LocalDateTime;

import static modules.consultation.entity.Appointment.isValidDoctorID;
import static modules.consultation.entity.Appointment.isValidPatientID;

/**
 *
 * @author hoche
 */
public class Consultation {
    private String consultationID;
    private String patientID;
    private String doctorID;
    private LocalDateTime dateTime;
    private String diagnosis;
    private String remarks;
    
    public Consultation(String consultationID, String patientID, String doctorID ,LocalDateTime dateTime, String diagnosis, String remarks){
        this.consultationID = consultationID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.dateTime = dateTime;
        this.diagnosis = diagnosis;
        this.remarks = remarks;
        
        if(!isValidConsultationID(consultationID)){
           throw new IllegalArgumentException("Invalid Appointment ID. Must be like C001."); 
        }
        
        if(!isValidPatientID(patientID)){
           throw new IllegalArgumentException("Invalid Patient ID. Must be like P001."); 
        }
        
        if(!isValidDoctorID(doctorID)){
           throw new IllegalArgumentException("Invalid Doctor ID. Must be like D001."); 
        }
    }
    
    //Validate ID format 
    public static boolean isValidConsultationID(String id){
        return id != null && id.matches("^C\\d{3}$");
    }
    
    public String getConsultationID(){
        return consultationID;
    }
    
    public String getPatientID(){
        return patientID;
    }
    public String getDoctorID(){
        return doctorID;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public String getDiagnosis(){ 
        return diagnosis; 
    }
    public String getRemarks(){
        return remarks; 
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    @Override
    public String toString() {
        return String.format("Consultation[id=%s, patient=%s, doctor=%s, time=%s, diagnosis=%s, remarks=%s]",
                consultationID, patientID, doctorID, dateTime, diagnosis, remarks);
    }
}
