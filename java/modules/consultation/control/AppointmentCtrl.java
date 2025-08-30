package modules.consultation.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.consultation.entity.Appointment;
import utils.Context;

public class AppointmentCtrl {
    public static List<Appointment> getAppointmentsByPatient(Context context, String patientID) {
        var result = new ArrayList<Appointment>();
        for (int i = 0; i < context.appointments.size(); i++) {
            Appointment appt = context.appointments.get(i);
            if (appt.getPatientID().trim().equalsIgnoreCase(patientID.trim())) {
                result.add(appt);
            }
        }
        return result;
    }

    public static ArrayList<Appointment> getAppointmentsByDoctor(Context context, String doctorID) {
        var result = new ArrayList<Appointment>();
        for (int i = 0; i < context.appointments.size(); i++) {
            Appointment appt = context.appointments.get(i);
            if (appt.getDoctorID().trim().equalsIgnoreCase(doctorID.trim())) {
                result.add(appt);
            }
        }
        return result;
    }
}
