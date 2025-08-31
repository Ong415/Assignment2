package modules.pharmacy.boundary;

import modules.pharmacy.control.PharmacyCtrl;
import modules.treatment.entity.Treatment;
import utils.Context;
import utils.UI;

import java.util.Objects;

public class CheckMedicineUI extends UI<Context> {
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

        clear();

        println("Treatment medication");

        for (var entry : treatment.getMedication()) {
            var medicine = entry.key();
            var quantity = entry.value();
            var stock = context.inventory.has(medicine) ? context.inventory.get(medicine) : 0;

            print(medicine.getName());
            print(": ");
            print(quantity <= stock ? "Available" : "Unavailable");
            println();
        }

        println();
        println("Summary: treatment medicine is " + (PharmacyCtrl.checkAvailability(context, treatment) ? "available" : "unavailable"));
        println();

        waitInput();

        return null;
    }
}
