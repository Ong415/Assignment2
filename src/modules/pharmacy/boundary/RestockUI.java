package modules.pharmacy.boundary;

import adt.implementations.HashMap;
import modules.pharmacy.control.PharmacyCtrl;
import modules.pharmacy.entity.Medicine;
import utils.Context;
import utils.UI;

public class RestockUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        var restock = new HashMap<Medicine, Integer>();

        while (true) {
            clear();
            println("Medicine restock");
            println();

            var medicine = readString("Enter medicine ID (empty to exit): ",
                    input -> input.isBlank() || context.medicines.has(input) ? null : "Medicine not found");

            if (medicine.isBlank()) break;

            var quantity = readInt("Enter quantity: ", NONZERO_INT);

            restock.insert(context.medicines.get(medicine), quantity);
        }

        PharmacyCtrl.restock(context, restock);

        return null;
    }
}
