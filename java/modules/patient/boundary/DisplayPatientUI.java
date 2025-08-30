package modules.patient.boundary;

import modules.patient.control.PatientCtrl;
import modules.patient.entity.Patient;
import utils.Context;
import utils.UI;

public class DisplayPatientUI extends UI<Context>{

    @Override
    protected UI<Context> display() {
        int totalAge = 0;
        int averageAge;

        println("===== Patient List =====");
        var patientList = PatientCtrl.getAllPatients(context);
        for (Patient patient: patientList){
            println(patient);
            totalAge += patient.getAge();
        }

        println("\n===== Patient Report =====");
        int patientCount = context.patients.size();
        averageAge = totalAge/patientCount;
        println("Total Patient Registered: " + patientCount);
        println("Average Patient Age: " + averageAge);

        waitInput();
        return null;
    }

}
