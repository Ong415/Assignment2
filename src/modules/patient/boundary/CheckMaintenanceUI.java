package modules.patient.boundary;

import modules.patient.control.MaintenanceCtrl;
import modules.patient.entity.Maintenance;
import utils.Context;
import utils.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CheckMaintenanceUI extends UI<Context> {

    private LocalDate startDate;
    private LocalDate lastDate;
    adt.interfaces.List<Maintenance> maintenanceList;

    private static boolean validateChoice(String choice) {
        return choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("p") || choice.equalsIgnoreCase("d") || choice.equalsIgnoreCase("pd");
    }

    @Override
    protected UI<Context> display() {
        println("=====Check Maintenance Record=====");
        println("1. (a) All");
        println("2. (p) Patient");
        println("3. (d) Date Range");
        println("4. (pd) Patient and Date Range");

        String choice = readString("Enter the code: ", input -> validateChoice(input) ? null : "Please type a,p,d or pd Only");

        if (choice.equalsIgnoreCase("a")) {
            maintenanceList = MaintenanceCtrl.getAllMaintenanceRecords(context);

            int totalMaintenanceRecords = context.maintenances.size();
            println("Total Maintenances Record: " + totalMaintenanceRecords);


        } else if (choice.equalsIgnoreCase("p")) {
            int patientId = readInt("Enter Patient ID: ", input -> context.patients.has(input) ? null : "Patient not existed");
            maintenanceList = MaintenanceCtrl.getMaintenanceRecordsByPatient(context, context.patients.get(patientId));

        } else if (choice.equalsIgnoreCase("d")) {
            startDate = null;
            lastDate = null;

            readString("Enter Start Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    startDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                return null;
            });

            readString("Enter Last Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    lastDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                return null;
            });

            maintenanceList = MaintenanceCtrl.getMaintenanceRecordsByDateRange(context, startDate, lastDate);

        } else if (choice.equalsIgnoreCase("pd")) {
            int patientId = readInt("Enter Patient ID: ", input -> context.patients.has(input) ? null : "Patient not existed");

            startDate = null;
            lastDate = null;

            readString("Enter Start Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    startDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                return null;
            });

            readString("Enter Last Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    lastDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                return null;
            });

            maintenanceList = MaintenanceCtrl.getMaintenanceRecordsByPatientAndDateRange(context, context.patients.get(patientId), startDate, lastDate);
        }

        if (maintenanceList != null && !maintenanceList.isEmpty()) {
            for (Maintenance maintenance : maintenanceList) {
                println(maintenance);
            }
        } else {
            println("No maintenance records found.");
        }


        waitInput();
        return null;
    }
}
