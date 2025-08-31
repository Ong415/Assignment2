package modules.pharmacy.boundary;

import modules.pharmacy.control.PharmacyCtrl;
import modules.treatment.entity.Treatment;
import utils.Context;
import utils.UI;

import java.util.Objects;

public class DispenseMedicineUI extends UI<Context> {
    private Treatment treatment;

    @Override
    protected UI<Context> display() {
        readString("Enter treatment ID: ", input -> {
            for (var t : context.treatments) {
                if (Objects.equals(t.getId(), input)) {
                    treatment = t;
                    return null;
                }
            }

            return "Treatment not found";
        });

        println(PharmacyCtrl.dispense(context, treatment) ? "Medicine dispensed successfully" : "Failed to dispense medicine");
        println();

        waitInput();

        return null;
    }
}
