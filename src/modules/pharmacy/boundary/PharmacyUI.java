package modules.pharmacy.boundary;

import adt.implementations.ArrayMap;
import utils.Context;
import utils.UI;

public class PharmacyUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("Pharmacy Management Module");
        println("1. View medicine inventory");
        println("2: Check medicine availability for a treatment");
        println("3: Calculate price of a treatment medication");
        println("4: Dispense medicine for a treatment");
        println("5: Restock medicine inventory");
        println("0: Exit");

        var options = new ArrayMap<Character, UI<Context>>();

        options.insert('1', new ViewInventoryUI());
        options.insert('2', new CheckMedicineUI());
        options.insert('3', new CalculatePriceUI());
        options.insert('4', new DispenseMedicineUI());
        options.insert('5', new RestockUI());
        options.insert('0', null);

        return readOption("Enter option: ", options);
    }
}
