package modules.consultation.entity;

import modules.doctor.entity.Doctor;
import modules.patient.entity.Patient;
import utils.UI;

import java.time.LocalDateTime;

/**
 * @author hoche
 */
public class Appointment {
    private final String id;
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDateTime dateTime;
    private String status;

    public Appointment(String id, Patient patient, Doctor doctor, LocalDateTime dateTime, String status) {
        if (!validateID(id)) {
            throw new IllegalArgumentException("Invalid Appointment ID. Must be like A001.");
        }

        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = status;
    }

    public static boolean validateID(String id) {
        return id != null && id.matches("^A\\d{3}$");
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s | P:%d | D:%s | %s | %s",
                id, patient.getId(), doctor.getId(),
                dateTime.format(UI.dateFormatter), status);
    }
}
