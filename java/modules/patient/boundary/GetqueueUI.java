package modules.patient.boundary;

import java.time.LocalDateTime;

import modules.patient.entity.PatientQueue;
import utils.Context;
import utils.UI;

public class GetqueueUI extends UI<Context>{
    private LocalDateTime now;

    @Override
    protected UI<Context> display() {
        int patientId = readInt("Enter Patient ID: ",input -> context.patients.find(input) == null ? "Patient not existed": null);
        now = LocalDateTime.now();
        context.queue.enqueue(new PatientQueue(context.patients.find(patientId), now));

        
        
        waitInput();
        return null;
    }
}
