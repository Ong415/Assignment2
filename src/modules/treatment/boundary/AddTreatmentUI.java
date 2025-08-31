package modules.treatment.boundary;

import adt.implementations.HashMap;
import modules.pharmacy.entity.Medicine;
import modules.treatment.entity.Treatment;
import utils.Context;
import utils.UI;

public class AddTreatmentUI extends UI<Context> {

    @Override
    protected UI<Context> display() {
        var id = readString("Enter Treatment ID: ", noValidation());
        var diagnosis = readString("Enter Diagnosis: ", noValidation());
        var medication = new HashMap<Medicine, Integer>();

        (new UI<Context>() {
            @Override
            protected UI<Context> display() {

                while (true) {
                    clear();
                    println("Treatment Medication");
                    println("Enter empty medicine to stop");

                    var medicine = readString("Enter medicine ID: ",
                            input -> input.isBlank() || context.medicines.has(input) ? null : "Invalid medicine ID");

                    if (medicine.isBlank()) return null;

                    var quantity = readInt("Enter medicine quantity: ", NONZERO_INT);

                    medication.insert(context.medicines.get(medicine), quantity);
                }
            }
        }).run(context);

        refresh();

        var date = readString("Enter Date (DD-MM-YYYY): ", noValidation());
        var docID = readString("Enter Doctor ID: ", noValidation());
        var patientId = readInt("Enter Patient ID: ",
                input -> context.patients.has(input) ? null : "Patient not found");

        context.treatments.add(new Treatment(id, diagnosis, medication, date, docID, context.patients.get(patientId)));
        println("Treatment added successfully!");

        waitInput();

        return null;
    }
}
