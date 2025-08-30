package modules.treatment.boundary;

import adt.implementations.HashMap;
import adt.interfaces.Map;
import modules.pharmacy.entity.Medicine;
import modules.treatment.entity.Treatment;
import utils.Context;
import utils.UI;

public class AddTreatmentUI extends UI<Context> {
    private class AddMedicineUI extends UI<Context> {
        @Override
        protected UI<Context> display() {
            println("Medication");
            println("Enter empty id if there is no more medicine");

            var id = readString("Medicine ID: ",
                    input -> (!input.isBlank()) || context.medicines.find(input) == null ? "Medicine ID not found!" : null);

            if (id.isBlank()) medicationComplete = true;
            else medication.insert(context.medicines.find(id), readInt("Quantity: ", 1, noValidation()));

            return null;
        }
    }

    private String id = null;
    private String diagnosis;
    private boolean medicationComplete = false;
    private final Map<Medicine, Integer> medication = new HashMap<>();

    @Override
    protected UI<Context> display() {
        if (id == null) {
            id = readString("Enter Treatment ID: ", noValidation());
            diagnosis = readString("Enter Diagnosis: ", noValidation());
        }
        if (!medicationComplete) return new AddMedicineUI();

        var med = readString("Enter Medication: ", noValidation());
        var date = readString("Enter Date (DD-MM-YYYY): ", noValidation());
        var docID = readString("Enter Doctor ID: ", noValidation());
        var patientId = readString("Enter Patient ID: ", noValidation());

        context.treatments.add(new Treatment(id, diagnosis, medication, date, docID, patientId));
        println("Treatment added successfully!");

        waitInput();

        return null;
    }
}
