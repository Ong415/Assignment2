package modules.patient.boundary;

import adt.implementations.HashMap;
import modules.patient.control.PatientCtrl;
import modules.patient.entity.Patient;
import utils.Context;
import utils.UI;

public class DisplayPatientUI extends UI<Context> {

    @Override
    protected UI<Context> display() {
        int totalAge = 0;
        int averageAge;

        println("1. All Patient");
        println("2. Specific Patient");

        var choice = new HashMap<Character, Boolean>();

        choice.insert('1', true);
        choice.insert('2', false);

        var all = readOption("Enter the code: ", choice);

        if (all) {
            println("===== Patient List =====");
            var patientList = PatientCtrl.getAllPatients(context);
            for (Patient patient : patientList) {
                println(patient);
                totalAge += patient.getAge();
            }

            println("\n===== Patient Report =====");
            int patientCount = context.patients.size();
            averageAge = totalAge / patientCount;
            println("Total Patient Registered: " + patientCount);
            println("Average Patient Age: " + averageAge);

        }
        else {
            int patientId = readInt("Enter Patient ID: ", input -> context.patients.has(input) ? null : "Patient not existed");
            Patient foundPatient = PatientCtrl.searchPatientByPatientId(context, patientId);
            println("===== Patient Found =====");
            println(foundPatient);
        }

        waitInput();
        return null;
    }
}
