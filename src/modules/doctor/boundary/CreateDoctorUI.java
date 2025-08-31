package modules.doctor.boundary;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.doctor.control.DoctorCtrl;
import utils.Context;
import utils.UI;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class CreateDoctorUI extends UI<Context> {
    public enum Operation {
        REGISTER,
        UPDATE_INFORMATION,
        UPDATE_SCHEDULE,
    }

    private final Operation operation;

    public CreateDoctorUI(Operation operation) {
        this.operation = operation;
    }

    private static boolean validateName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))) return false;
        }
        return true;
    }

    @Override
    protected UI<Context> display() {
        switch (operation) {
            case REGISTER -> {
                println("===== Create Doctor Information =====\n");
                String name = readString("Enter Name: ", input -> validateName(input) ? null : "No digits allowed!");
                int age = readInt("Enter Age: ", input -> input > 0 ? null : "Age must be more than 0!");
                String phoneNo = readString("Enter Phone Number: ", noValidation());
                String email = readString("Enter Email Address: ", noValidation());

                clear();
                println("===== Create Doctor Schedule =====\n");
                println("1. Morning Schedule (07:00 - 12:00)");
                println("2. Afternoon Schedule (12:00 - 17:00)");
                println("3. Night Schedule (17:00 - 22:00)");
                println("0. No Schedule");
                println();
                println("Doctor: " + name);

                List<Integer> selections = new ArrayList<>(7);

                for (int i = 1; i <= 7; i++) {
                    DayOfWeek dayOfWeek = DayOfWeek.of(i);
                    int selection = readInt(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ": ", input -> input >= 0 && input <= 3 ? null : "Schedule Choice must be 0-4!");

                    selections.add(selection);
                }

                DoctorCtrl.addDoctor(context, name, age, phoneNo, email, selections);
                println("New Doctor Registered Successfully!");
            }

            case UPDATE_INFORMATION -> {
                println("===== Update Doctor Information =====\n");
                String doctorId = readString("Enter Doctor ID (eg D1000): ", noValidation()).toUpperCase();

                if (DoctorCtrl.containsDoctorID(context, doctorId)) {
                    String name = readString("Enter Name: ", input -> validateName(input) ? null : "No digits allowed!");
                    int age = readInt("Enter Age: ", input -> input > 0 ? null : "Age must be more than 0!");
                    String phoneNo = readString("Enter Phone Number: ", noValidation());
                    String email = readString("Enter Email Address: ", noValidation());

                    DoctorCtrl.updateDoctor(context, doctorId, name, age, phoneNo, email);

                    println("Doctor Information Updated Successfully!");
                } else {
                    println("Doctor ID Doesn't Exist!");
                }
            }

            case UPDATE_SCHEDULE -> {
                println("===== Update Doctor Schedule =====\n");
                String doctorId = readString("Enter Doctor ID (eg D1000): ", noValidation()).toUpperCase();

                if (DoctorCtrl.containsDoctorID(context, doctorId)) {
                    clear();
                    println("===== Update Doctor Schedule =====\n");
                    println("1. Morning Schedule (06:00 - 12:00)");
                    println("2. Afternoon Schedule (12:00 - 18:00)");
                    println("3. Night Schedule (18:00 - 24:00)");
                    println("0. No Schedule");
                    println();
                    println("Doctor: " + context.doctors.get(doctorId).getName());

                    List<Integer> selections = new ArrayList<>(7);

                    for (int i = 1; i <= 7; i++) {
                        DayOfWeek dayOfWeek = DayOfWeek.of(i);
                        int selection = readInt(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ": ", input -> input >= 0 && input <= 3 ? null : "Schedule Choice must be 0-4!");

                        selections.add(selection);
                    }

                    DoctorCtrl.updateDoctor(context, doctorId, selections);

                    println("Doctor Schedule Updated Successfully!");
                } else {
                    println("Doctor ID Doesn't Exist!");
                }
            }
        }

        waitInput();

        return null;
    }
}
