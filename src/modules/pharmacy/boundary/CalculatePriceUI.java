package modules.pharmacy.boundary;

import modules.pharmacy.control.PharmacyCtrl;
import modules.treatment.entity.Treatment;
import utils.Context;
import utils.UI;

import java.util.Objects;

public class CalculatePriceUI extends UI<Context> {
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

        println("Price breakdown");

        for (var entry : treatment.getMedication()) {
            var medicine = entry.key();
            var quantity = entry.value();
            println(medicine.getName() + " * " + quantity + " = RM " + String.format("%.2f", medicine.getPrice() * quantity));
        }

        println();
        println("Total: RM " + String.format("%.2f", PharmacyCtrl.calculatePrice(treatment)));
        println();

        waitInput();

        return null;
    }
}
