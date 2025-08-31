package modules.pharmacy.boundary;

import utils.Context;
import utils.UI;

public class ViewInventoryUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("Medicine inventory");
        println();

        for (var entry : context.inventory) {
            var medicine = entry.key();
            var stock = entry.value();
            println(medicine.getName() + ": " + stock);
        }

        println();

        waitInput();

        return null;
    }
}
