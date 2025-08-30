package modules.consultation.boundary;

import modules.consultation.control.AppointmentCtrl;
import utils.Context;
import utils.UI;

public class ViewAppointmentByPatientUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        var pid = readString("Enter Patient ID: ", noValidation());
        var list = AppointmentCtrl.getAppointmentsByPatient(context, pid);
        println();
        println("Appointments:");
        if (list.isEmpty()) {
            println("No appointments found for " + pid);
        } else {
            for (int i = 0; i < list.size(); i++) {
                println(list.get(i));
            }
        }

        waitInput();

        return null;
    }
}
