package modules.treatment.boundary;

import modules.treatment.control.TreatmentCtrl;
import utils.Context;
import utils.UI;

public class DisplayTreatmentUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        if (context.treatments.isEmpty()) {
            readString("No treatments recorded yet.", noValidation());
            return null;
        }

        var patientId = readInt("Enter Patient ID: ", input -> context.patients.has(input) ? null : "Patient not found");
        var list = TreatmentCtrl.getTreatmentsForPatient(context, context.patients.get(patientId));

        println("Treatment count: " + list.size());
        for (var t : list) {
            println("Treatment ID: " + t.getId());
        }

        waitInput();

        return null;
    }
}
