package modules.consultation.boundary;

import adt.implementations.HashMap;
import adt.interfaces.Map;
import utils.Context;
import utils.UI;

public class ConsultationUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("=== Consultation System Menu ===");
        println();
        println("1. Add Consultation");
        println("2. Add Appointment");
        println("3. View Consultations by Patient");
        println("4. View Appointments by Patient");
        println("5. View Appointments by Doctor");
        println("0. Exit");
        println();

        Map<Character, UI<Context>> options = new HashMap<>();

        options.insert('1', new AddConsultationUI());
        options.insert('2', new AddAppointmentUI());
        options.insert('3', new ViewConsultationByPatientUI());
        options.insert('4', new ViewAppointmentByPatientUI());
        options.insert('5', new ViewAppointmentByDoctorUI());
        options.insert('0', null);

        return readOption("Enter choice: ", options);
    }
}
