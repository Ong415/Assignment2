package modules.consultation.entity;

import modules.doctor.entity.Doctor;
import modules.patient.entity.Patient;
import utils.UI;

import java.time.LocalDateTime;

/**
 * @author hoche
 */
public class Consultation {
    private final String id;
    private final Patient patient;
    private final Doctor doctor;
    private LocalDateTime dateTime;
    private String diagnosis;
    private String remarks;

    public Consultation(String id, Patient patient, Doctor doctor, LocalDateTime dateTime, String diagnosis, String remarks) {
        if (!validateID(id)) {
            throw new IllegalArgumentException("Invalid Consultation ID. Must be like C001.");
        }

        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.diagnosis = diagnosis;
        this.remarks = remarks;
    }

    //Validate ID format 
    public static boolean validateID(String id) {
        return id != null && id.matches("^C\\d{3}$");
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
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
                id, patient.getId(), doctor.getId(), dateTime.format(UI.dateFormatter), diagnosis, remarks);
    }
}
