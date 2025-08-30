package modules.pharmacy.control;

import modules.treatment.entity.Treatment;
import utils.Context;

public class PharmacyCtrl {
    public static boolean checkAvailability(Context context, Treatment treatment) {
        for (var entry : treatment.getMedication()) {
            var medicine = entry.key();
            var quantity = entry.value();
            var stock = context.inventory.get(medicine);
            if (stock == null) stock = 0;
            if (stock < quantity) return false;
        }

        return true;
    }

    public static double calculatePrice(Treatment treatment) {
        double sum = 0;

        for (var medicine : treatment.getMedication()) {
            sum += medicine.key().getPrice();
        }

        return sum;
    }

    public static boolean dispense(Context context, Treatment treatment) {
        if (checkAvailability(context, treatment)) {
            for (var entry : treatment.getMedication()) {
                var medicine = entry.key();
                var quantity = entry.value();
                var stock = context.inventory.get(medicine);
                context.inventory.insert(medicine, stock - quantity);
            }

            return true;
        } else {
            return false;
        }
    }
}
