
package modules.consultation.entity;

import modules.patient.entity.Patient;
import utils.UI;

import java.time.LocalDateTime;

/**
 * @author hoche
 */
public class Appointment {
    private final String id;
    private final Patient patient;
    private final String doctorID;
    private final LocalDateTime dateTime;
    private String status;

    public Appointment(String id, Patient patient, String doctorID, LocalDateTime dateTime, String status) {
        if (!validateID(id)) {
            throw new IllegalArgumentException("Invalid Appointment ID. Must be like A001.");
        }

        if (!isValidDoctorID(doctorID)) {
            throw new IllegalArgumentException("Invalid Doctor ID. Must be like D001.");
        }

        this.id = id;
        this.patient = patient;
        this.doctorID = doctorID;
        this.dateTime = dateTime;
        this.status = status;
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

    public Patient getPatient() {
        return patient;
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
        return String.format("%s | P:%d | D:%s | %s | %s",
                id, patient.getId(), doctorID,
                dateTime.format(UI.dateFormatter), status);
    }
}
