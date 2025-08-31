package modules.consultation.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.consultation.entity.Appointment;
import modules.doctor.entity.Doctor;
import modules.patient.entity.Patient;
import utils.Context;

public class AppointmentCtrl {
    public static List<Appointment> getAppointmentsByPatient(Context context, Patient patient) {
        var result = new ArrayList<Appointment>();
        for (int i = 0; i < context.appointments.size(); i++) {
            Appointment appt = context.appointments.get(i);
            if (appt.getPatient() == patient) {
                result.add(appt);
            }
        }
        return result;
    }

    public static ArrayList<Appointment> getAppointmentsByDoctor(Context context, Doctor doctor) {
        var result = new ArrayList<Appointment>();
        for (int i = 0; i < context.appointments.size(); i++) {
            Appointment appt = context.appointments.get(i);
            if (appt.getDoctor() == doctor) {
                result.add(appt);
            }
        }
        return result;
    }
}
