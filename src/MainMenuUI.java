import adt.implementations.ArrayMap;
import modules.consultation.boundary.ConsultationUI;
import modules.patient.boundary.PatientUI;
import modules.pharmacy.boundary.PharmacyUI;
import modules.treatment.boundary.TreatmentUI;
import utils.Context;
import utils.UI;

public class MainMenuUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("Main Menu");
        println("1: Patient Management Module");
        println("2: Doctor Management Module");
        println("3: Consultation Management Module");
        println("4: Medical Treatment Management Module");
        println("5: Pharmacy Management Module");
        println("6: Exit");

        var options = new ArrayMap<Character, UI<Context>>();

        options.insert('1', new PatientUI());
        options.insert('3', new ConsultationUI());
        options.insert('4', new TreatmentUI());
        options.insert('5', new PharmacyUI());
        options.insert('6', null);

        var option = readOption("Enter option: ", options);

        if (option == null) {
            clear();
            println("Thank you for using the application.");
        }

        return option;
    }
}
