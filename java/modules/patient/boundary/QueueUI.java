package modules.patient.boundary;

import java.time.LocalDateTime;

import modules.patient.control.QueueCtrl;
import modules.patient.entity.PatientQueue;
import utils.Context;
import utils.UI;

public class QueueUI extends UI<Context>{
    private LocalDateTime now;

    @Override
    protected UI<Context> display() {
        println("=====Queue Management=====");
        println("1. Add patient to queue");
        println("2. Call next patient");
        println("3. Complete patient");
        println("4. View current queue");
        
        char choice = readChar("Enter choice (1/2/3): ", input -> 
            (input == '1' || input == '2' || input == '3' || input =='4') ? null : "Please enter 1, 2, or 3");
        
        if (choice == '1') {
            // Enqueue patient
            int patientId = readInt("Enter Patient ID: ", input -> 
                context.patients.find(input) == null ? "Patient not existed" : null);
            now = LocalDateTime.now();
            QueueCtrl.enqueue(context, new PatientQueue(context.patients.find(patientId), now));
            println("Patient added to queue successfully!");
            
        } else if (choice == '2') {
            // Dequeue patient
            PatientQueue nextPatient = QueueCtrl.dequeue(context);
            if (nextPatient != null) {
                println("Next patient called:");
                println(nextPatient);
                println("Patient status set to: " + nextPatient.getStatus());
            } else {
                println("No patients in queue.");
            }
            
        }else if (choice == '3'){
            int queueNumber = readInt("Enter Queue Number to complete: ", 
            input -> {
                var pq = QueueCtrl.findByQueueNumber(context, input);
                if (pq == null) {
                    return "Queue number not found";
                }
                String status = pq.getStatus();
                if ("Completed".equals(status)) {
                    return "Patient is already completed";
                }
                if (!"In Progress".equals(status)) {
                    return "Patient must be 'In Progress' to complete. Current status: " + status;
                }
                return null; // Valid - patient is in progress
            });
        
            // Attempt to complete the patient
            boolean success = QueueCtrl.completePatient(context, queueNumber);
            
            if (success) {
                println("Patient in queue number " + queueNumber + " has been successfully completed.");
            } else {
                println("Failed to complete patient. Patient must be 'In Progress' status.");
            }
        } 
        else if (choice == '4') {
            // View queue
            println("Current Queue:");
        }
        
        // Always show current queue status
        var queueList = QueueCtrl.getAllQueueList(context);
        if (queueList.isEmpty()) {
            println("Queue is empty.");
        } else {
            println("\nCurrent Queue Status:");
            for (PatientQueue queue : queueList) {
                println(queue);
            }
        }
        
        waitInput();
        return null;
    }
}
