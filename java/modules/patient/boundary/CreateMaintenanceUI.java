package modules.patient.boundary;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import modules.patient.entity.Maintenance;
import utils.Context;
import utils.UI;

public class CreateMaintenanceUI extends UI<Context>{
    private boolean createFlag;
    private LocalDate createDate;

    public CreateMaintenanceUI(boolean createFlag){
        this.createFlag = createFlag;
    }

    @Override
    protected UI<Context> display() {
        createDate = null;

        if(createFlag){
            int patientId = readInt("Enter Patient ID: ",input -> context.patients.find(input) == null ? "Patient not existed": null);
        
            readString("Enter Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    createDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                    return null;
            });

            String details = readString("Enter the details: " , noValidation());

            context.maintenances.register(new Maintenance(context.maintenanceCounter.get(), 
                context.patients.find(patientId), createDate,details ));

            println("Maintenance report created succesfully!");
        }
        else{
            int maintenanceId = readInt("Enter Maintenance ID: ",input -> context.maintenances.find(input) == null ? "Maintainence not existed": null);
            var maintenance = context.maintenances.find(maintenanceId);
            
            readString("Enter Date (" + DATE_FORMAT2 + "): ", input -> {
                try {
                    createDate = LocalDate.parse(input, dateFormatter2);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }
                    return null;
            });
            if (createDate!= null){
                maintenance.setDate(createDate);
            }

            String details = readString("Enter the details: " , noValidation());
            if(!details.isBlank()){
                maintenance.setDetails(details);
            }
            
            println("Maintenance record updated successfully!");
        }
        waitInput();
        return null;
    }
    
}
