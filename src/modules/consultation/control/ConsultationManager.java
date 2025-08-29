package modules.consultation.control;

import adt.interfaces.List;
import adt.implementations.ArrayList;
import modules.consultation.entity.*;

import java.time.LocalDateTime;

public class ConsultationManager {
    private final List<Consultation> consultations = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    // ---------------- Core Methods ----------------
    public void addConsultation(Consultation c) {
        consultations.add(c);
    }

    public ArrayList<Consultation> getConsultationsByPatient(String patientID) {
        ArrayList<Consultation> result = new ArrayList<>();
        for (int i = 0; i < consultations.size(); i++) {
            Consultation c = consultations.get(i);
            if (c.getPatientID().trim().equalsIgnoreCase(patientID.trim())) {
                result.add(c);
            }
        }
        return result;
    }

    // Appointment functions
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public ArrayList<Appointment> getAppointmentsByPatient(String patientID) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appt = appointments.get(i);
            if (appt.getPatientID().trim().equalsIgnoreCase(patientID.trim())) {
                result.add(appt);
            }
        }
        return result;
    }

    public ArrayList<Appointment> getAppointmentsByDoctor(String doctorID) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appt = appointments.get(i);
            if (appt.getDoctorID().trim().equalsIgnoreCase(doctorID.trim())) {
                result.add(appt);
            }
        }
        return result;
    }

    // ---------------- Validation Methods ----------------
    

    /** Check if consultation time is valid (8am–6pm, max 5 per day) */
    public boolean validateConsultationTime(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        if (hour < 8 || hour >= 18) {
            return false; // not in working hours
        }

        int count = 0;
        for (int i = 0; i < consultations.size(); i++) {
            Consultation c = consultations.get(i);
            if (c.getDateTime().toLocalDate().equals(dateTime.toLocalDate())) {
                count++;
            }
        }
        return count < 5; // max 5 per day
    }

    /** Check if the consultation time is already taken */
    public boolean isTimeAvailable(LocalDateTime dateTime) {
        for (int i = 0; i < consultations.size(); i++) {
            Consultation c = consultations.get(i);
            if (c.getDateTime().equals(dateTime)) {
                return false; // time already booked
            }
        }
        return true;
    }

    /** Check if doctor is available at that time */
    public boolean isDoctorAvailable(String doctorId, LocalDateTime dateTime) {
        for (int i = 0; i < consultations.size(); i++) {
            Consultation c = consultations.get(i);
            if (c.getDoctorID().equalsIgnoreCase(doctorId) &&
                c.getDateTime().equals(dateTime)) {
                return false; // doctor already booked
            }
        }
        return true;
    }
}