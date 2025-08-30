package modules.treatment.boundary;

import modules.treatment.control.TreatmentCtrl;
import utils.Context;
import utils.UI;

public class RemoveTreatmentUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        if (context.treatments.isEmpty()) {
            readString("No treatments recorded yet.", noValidation());
            return null;
        }

        var treatmentId = readString("Enter Treatment Id to remove: ", noValidation());
        if (TreatmentCtrl.removeTreatment(context, treatmentId) == null)
            println("Treatment not found");
        else
            println("Treatment removed successfully");

        waitInput();

        return null;
    }
}
