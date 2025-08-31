package modules.consultation.boundary;

import modules.consultation.control.AppointmentCtrl;
import utils.Context;
import utils.UI;

public class ViewAppointmentByDoctorUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        var did = readString("Enter Doctor ID: ", input -> context.doctors.has(input) ? null : "Doctor not found");
        var list = AppointmentCtrl.getAppointmentsByDoctor(context, context.doctors.get(did));
        println();
        println("Appointments:");

        if (list.isEmpty()) {
            println("No appointments found for " + did);
        } else {
            for (int i = 0; i < list.size(); i++) {
                println(list.get(i));
            }
        }

        waitInput();

        return null;
    }
}
