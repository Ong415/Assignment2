package modules.pharmacy.control;

import adt.interfaces.Map;
import modules.pharmacy.entity.Medicine;
import modules.treatment.entity.Treatment;
import utils.Context;

// written by Wilson Ang Shao En

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

    public static void restock(Context context, Map<Medicine, Integer> stocks) {
        for (var entry : stocks) {
            var medicine = entry.key();
            var quantity = entry.value();
            quantity += context.inventory.has(medicine) ? context.inventory.get(medicine) : 0;
            context.inventory.insert(medicine, quantity);
        }
    }
}
