package modules.treatment.boundary;

import modules.treatment.control.TreatmentCtrl;
import utils.Context;
import utils.UI;

public class ReportDoctorTreatmentsUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        if (context.treatments.isEmpty()) {
            readString("No treatment records yet.", noValidation());
            return null;
        }
        var counts = TreatmentCtrl.reportDoctorTreatments(context);
        println();
        println("--- Report: Treatments per Doctor ---");
        for (var entry : counts) {
            println("Doctor ID: " + entry.key() + " , " + entry.value() + " treatments");
        }

        waitInput();

        return null;
    }
}
