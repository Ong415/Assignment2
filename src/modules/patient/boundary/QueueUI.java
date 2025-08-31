package modules.patient.boundary;

import adt.implementations.HashMap;
import modules.patient.control.QueueCtrl;
import modules.patient.entity.PatientQueue;
import utils.Context;
import utils.UI;

import java.time.LocalDateTime;

public class QueueUI extends UI<Context> {
    private enum Choice {
        ADD,
        CALL,
        VIEW,
    }

    private LocalDateTime now;

    @Override
    protected UI<Context> display() {
        println("===== Queue Management =====");
        println("1. Add patient to queue");
        println("2. Call next patient");
        println("3. View current queue");

        var choice = new HashMap<Character, Choice>();

        choice.insert('1', Choice.ADD);
        choice.insert('2', Choice.CALL);
        choice.insert('3', Choice.VIEW);

        switch (readOption("Enter choice (1/2/3): ", choice)) {
            case ADD -> {
                // Enqueue patient
                int patientId = readInt("Enter Patient ID: ", input ->
                        context.patients.has(input) ? null : "Patient not existed");
                now = LocalDateTime.now();
                QueueCtrl.enqueue(context, new PatientQueue(context.patients.get(patientId), now));
                println("Patient added to queue successfully!");

            }

            case CALL -> {
                // Dequeue patient
                PatientQueue nextPatient = QueueCtrl.dequeue(context);
                if (nextPatient != null) {
                    println("Next patient called:");
                    println(nextPatient);
                    println("Patient status set to: " + nextPatient.getStatus());
                } else {
                    println("No patients in queue.");
                }
            }

            // View queue
            case VIEW -> println("");
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
