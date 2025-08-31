package modules.treatment.boundary;

import modules.treatment.control.TreatmentCtrl;
import utils.Context;
import utils.UI;

public class SearchTreatmentUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        if (context.treatments.isEmpty()) {
            readString("No treatments recorded yet.", noValidation());
            return null;
        }

        var docId = readString("Enter Doctor ID: ", input -> context.doctors.has(input) ? null : "Doctor not found");
        var list = TreatmentCtrl.getDoctorTreatments(context, context.doctors.get(docId));

        println("Treatment count: " + list.size());
        for (var t : list) {
            println("Treatment ID " + t.getId());
        }

        waitInput();

        return null;
    }
}
