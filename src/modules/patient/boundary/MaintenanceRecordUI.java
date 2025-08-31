package modules.patient.boundary;

import modules.patient.control.MaintenanceCtrl;
import utils.Context;
import utils.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MaintenanceRecordUI extends UI<Context> {
    private final boolean createFlag;
    private LocalDate createDate;

    public MaintenanceRecordUI(boolean createFlag) {
        this.createFlag = createFlag;
    }

    @Override
    protected UI<Context> display() {
        createDate = null;

        if (createFlag) {
            println("===== Create Maintenance Record ===== \n");
            int patientId = readInt("Enter Patient ID: ", input -> context.patients.has(input) ? null : "Patient not existed");

            readString("Enter Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    createDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                return null;
            });

            String details = readString("Enter the details: ", noValidation());

            MaintenanceCtrl.addMaintenanceRecord(context, context.patients.get(patientId), createDate, details);

            println("Maintenance report created successfully!");
        } else {
            println("===== Update Maintenance Record ===== \n");
            int maintenanceId = readInt("Enter Maintenance ID: ", input -> context.maintenances.has(input) ? null : "Maintenance not existed");
            var maintenance = context.maintenances.get(maintenanceId);

            readString("Enter Date (" + DATE_FORMAT2 + "): ", input -> {
                if (input.trim().isEmpty()) {
                    createDate = null; // Keep existing date
                    return null; // Allow empty input
                }
                try {
                    createDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                return null;
            });
            if (createDate != null) {
                maintenance.setDate(createDate);
            }

            String details = readString("Enter the details: ", noValidation());
            if (!details.isBlank()) {
                maintenance.setDetails(details);
            }

            println("Maintenance record updated successfully!");
        }
        waitInput();
        return null;
    }

}
