package modules.patient.boundary;

import modules.patient.control.PatientCtrl;
import modules.patient.entity.Patient;
import utils.Context;
import utils.UI;

public class DisplayPatientUI extends UI<Context>{

    @Override
    protected UI<Context> display() {
        var patientList = PatientCtrl.getAllPatients(context);
        for (Patient patient: patientList){
            println(patient);
        }

        waitInput();
        return null;
    }

}
