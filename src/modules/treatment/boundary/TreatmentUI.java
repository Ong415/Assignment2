package modules.treatment.boundary;

import adt.implementations.HashMap;
import utils.Context;
import utils.UI;

public class TreatmentUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println();
        println("--- Medical Treatment Management ---");
        println("1. Add Treatment Record");
        println("2. View Patient Treatment History (by Patient ID)");
        println("3. Search Treatment by Doctor ID");
        println("4. Remove Treatment by Index");
        println("5. Report: Count Treatments per Patient");
        println("6. Report: Count Treatments per Doctor");
        println("7. Exit");
        println();

        var options = new HashMap<Character, UI<Context>>();

        options.insert('1', new AddTreatmentUI());
        options.insert('2', new DisplayTreatmentUI());
        options.insert('3', new SearchTreatmentUI());
        options.insert('4', new RemoveTreatmentUI());
        options.insert('5', new ReportPatientTreatmentsUI());
        options.insert('6', new ReportDoctorTreatmentsUI());
        options.insert('7', null);

        return readOption("Enter choice: ", options);
    }
}