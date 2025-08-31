package modules.consultation.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.consultation.entity.Consultation;
import modules.doctor.entity.Doctor;
import modules.patient.entity.Patient;
import utils.Context;

import java.time.LocalDateTime;

public class ConsultationCtrl {
    public static List<Consultation> getConsultationsByPatient(Context context, Patient patient) {
        var result = new ArrayList<Consultation>();
        for (int i = 0; i < context.consultations.size(); i++) {
            var c = context.consultations.get(i);
            if (c.getPatient() == patient) {
                result.add(c);
            }
        }
        return result;
    }

    // ---------------- Validation Methods ----------------

    /**
     * Check if consultation time is valid (8am–6pm, max 5 per day)
     */
    public static boolean validateConsultationTime(Context context, LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        if (hour < 8 || hour >= 18) {
            return false; // not in working hours
        }

        int count = 0;
        for (int i = 0; i < context.consultations.size(); i++) {
            Consultation c = context.consultations.get(i);
            if (c.getDateTime().toLocalDate().equals(dateTime.toLocalDate())) {
                count++;
            }
        }
        return count < 5; // max 5 per day
    }

    /**
     * Check if the consultation time is already taken
     */
    public static boolean isTimeAvailable(Context context, LocalDateTime dateTime) {
        for (int i = 0; i < context.consultations.size(); i++) {
            Consultation c = context.consultations.get(i);
            if (c.getDateTime().equals(dateTime)) {
                return false; // time already booked
            }
        }
        return true;
    }

    /**
     * Check if doctor is available at that time
     */
    public static boolean isDoctorAvailable(Context context, Doctor doctor, LocalDateTime dateTime) {
        for (int i = 0; i < context.consultations.size(); i++) {
            Consultation c = context.consultations.get(i);
            if (c.getDoctor() == doctor && c.getDateTime().equals(dateTime)) {
                return false; // doctor already booked
            }
        }
        return true;
    }
}