/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.consultation.entity;

import utils.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hoche
 */
public class Appointment {
    private final String id;
    private String patientID;
    private String doctorID;
    private final LocalDateTime dateTime;
    private String status;

    public Appointment(String id, String patientID, String doctorID, LocalDateTime dateTime, String status) {
        if (!validateID(id)) {
            throw new IllegalArgumentException("Invalid Appointment ID. Must be like A001.");
        }

        if (!isValidPatientID(patientID)) {
            throw new IllegalArgumentException("Invalid Patient ID. Must be like P001.");
        }

        if (!isValidDoctorID(doctorID)) {
            throw new IllegalArgumentException("Invalid Doctor ID. Must be like D001.");
        }

        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.dateTime = dateTime;
        this.status = status;
    }

    //Validate ID format 
    public static boolean isValidPatientID(String id) {
        return id != null && id.matches("^P\\d{3}$");
    }

    public static boolean validateID(String id) {
        return id != null && id.matches("^A\\d{3}$");
    }

    public static boolean isValidDoctorID(String id) {
        return id != null && id.matches("^D\\d{3}$");
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s | P:%s | D:%s | %s | %s",
                id, patientID, doctorID,
                dateTime.format(UI.dateFormatter), status);
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
