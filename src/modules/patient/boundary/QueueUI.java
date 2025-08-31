package modules.patient.boundary;

import modules.patient.control.QueueCtrl;
import modules.patient.entity.PatientQueue;
import utils.Context;
import utils.UI;

import java.time.LocalDateTime;

public class QueueUI extends UI<Context> {
    private LocalDateTime now;

    @Override
    protected UI<Context> display() {
        println("===== Queue Management =====");
        println("1. Add patient to queue");
        println("2. Call next patient");
        println("3. View current queue");

        char choice = readChar("Enter choice (1/2/3): ", input ->
                (input == '1' || input == '2' || input == '3') ? null : "Please enter 1, 2, or 3");

        if (choice == '1') {
            // Enqueue patient
            int patientId = readInt("Enter Patient ID: ", input ->
                    context.patients.has(input) ? null : "Patient not existed");
            now = LocalDateTime.now();
            QueueCtrl.enqueue(context, new PatientQueue(context.patients.get(patientId), now));
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

        } else if (choice == '3') {
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
