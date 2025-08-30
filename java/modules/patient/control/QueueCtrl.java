package modules.patient.control;

import adt.implementations.ArrayList;
import adt.implementations.LinkedQueue;
import adt.interfaces.List;
import modules.patient.entity.PatientQueue;
import utils.Context;

public class QueueCtrl {
    public static PatientQueue dequeue(Context context) {
        var pq = context.queue.dequeue();
        if (pq != null) {
            pq.setStatus("In Progress");
        }
        return pq;
    }

    public static void enqueue(Context context, PatientQueue patientQueue) {
        // Set queue number
        patientQueue.setQueueNumber(context.queueCounter.get());

        // Check for time conflicts
        var proposedTime = patientQueue.getQueueDateTime();

        // Create temporary queue for checking times
        var tempQueue = new LinkedQueue<PatientQueue>();
        boolean timeExists = false;

        while (!context.queue.isEmpty()) {
            var current = context.queue.dequeue();
            if (current.getQueueDateTime().equals(proposedTime)) {
                timeExists = true;
            }
            tempQueue.enqueue(current);
        }

        // Restore original queue
        while (!tempQueue.isEmpty()) {
            context.queue.enqueue(tempQueue.dequeue());
        }

        // If time conflict exists, add one minute
        if (timeExists) {
            patientQueue.setQueueDateTime(proposedTime.plusMinutes(1));
        }

        context.queue.enqueue(patientQueue);
    }

    // Additional methods

    public static PatientQueue findByQueueNumber(Context context, int queueNumber) {
        var tempQueue = new LinkedQueue<PatientQueue>();
        PatientQueue found = null;

        while (!context.queue.isEmpty()) {
            var current = context.queue.dequeue();
            if (current.getPatient().getId() == queueNumber) {
                found = current;
            }
            tempQueue.enqueue(current);
        }

        // Restore queue
        while (!tempQueue.isEmpty()) {
            context.queue.enqueue(tempQueue.dequeue());
        }

        return found;
    }

    public static boolean completePatient(Context context, int queueNumber) {
        var pq = findByQueueNumber(context, queueNumber);
        if (pq != null && "In Progress".equals(pq.getStatus())) {
            pq.setStatus("Completed");
            return true;
        }
        return false;
    }
    
    public static List<PatientQueue> getAllQueueList(Context context) {
        var tempQueue = new LinkedQueue<PatientQueue>();
        var queueList = new ArrayList<PatientQueue>();

        // Dequeue all items and add to list
        while (!context.queue.isEmpty()) {
            var current = context.queue.dequeue();
            queueList.add(current);
            tempQueue.enqueue(current);
        }

        // Restore original queue
        while (!tempQueue.isEmpty()) {
            context.queue.enqueue(tempQueue.dequeue());
        }

        return queueList;
    }
}