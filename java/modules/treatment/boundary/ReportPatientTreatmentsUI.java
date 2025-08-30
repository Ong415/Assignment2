package modules.treatment.boundary;

import modules.treatment.control.TreatmentCtrl;
import utils.Context;
import utils.UI;

public class ReportPatientTreatmentsUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        if (context.treatments.isEmpty()) {
            readString("No treatment records yet.", noValidation());
            return null;
        }
        var counts = TreatmentCtrl.reportPatientTreatments(context);
        println();
        println("--- Report: Treatments per Patient ---");
        for (var entry : counts) {
            println("Patient ID: " + entry.key() + " , " + entry.value() + " treatments");
        }

        waitInput();

        return null;
    }
}
