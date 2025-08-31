package modules.doctor.boundary;

import adt.implementations.HashMap;
import utils.Context;
import utils.UI;

public class DoctorUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("=== Doctor Management ===");
        println();
        println("1. View Doctor List");
        println("2. Register New Doctor");
        println("3. Update Doctor Information");
        println("4. View Doctor Schedule");
        println("5. Update Doctor Schedule");
        println("6. Check Doctor Availability");
        println("0. Exit");

        var choice = new HashMap<Character, UI<Context>>();

        choice.insert('1', new ViewDoctorListUI());
        choice.insert('2', new CreateDoctorUI(CreateDoctorUI.Operation.REGISTER));
        choice.insert('3', new CreateDoctorUI(CreateDoctorUI.Operation.UPDATE_INFORMATION));
        choice.insert('4', new ViewScheduleUI());
        choice.insert('5', new CreateDoctorUI(CreateDoctorUI.Operation.UPDATE_SCHEDULE));
        choice.insert('6', new ViewDoctorAvailabilityUI());

        choice.insert('0', null);

        return readOption("Enter: ", choice);
    }
}
