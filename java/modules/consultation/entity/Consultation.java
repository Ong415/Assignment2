/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.consultation.entity;

import utils.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static modules.consultation.entity.Appointment.isValidDoctorID;
import static modules.consultation.entity.Appointment.isValidPatientID;

/**
 *
 * @author hoche
 */
public class Consultation {
    private final String id;
    private String patientID;
    private String doctorID;
    private LocalDateTime dateTime;
    private String diagnosis;
    private String remarks;

    public Consultation(String id, String patientID, String doctorID, LocalDateTime dateTime, String diagnosis, String remarks) {
        if (!validateID(id)) {
            throw new IllegalArgumentException("Invalid Consultation ID. Must be like C001.");
        }

        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.dateTime = dateTime;
        this.diagnosis = diagnosis;
        this.remarks = remarks;

        if (!isValidPatientID(patientID)) {
            throw new IllegalArgumentException("Invalid Patient ID. Must be like P001.");
        }

        if (!isValidDoctorID(doctorID)) {
            throw new IllegalArgumentException("Invalid Doctor ID. Must be like D001.");
        }
    }

    //Validate ID format 
    public static boolean validateID(String id) {
        return id != null && id.matches("^C\\d{3}$");
    }

    public String getId() {
        return id;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("Consultation[id=%s, patient=%s, doctor=%s, time=%s, diagnosis=%s, remarks=%s]",
                id, patientID, doctorID, dateTime.format(UI.dateFormatter), diagnosis, remarks);
    }
}
