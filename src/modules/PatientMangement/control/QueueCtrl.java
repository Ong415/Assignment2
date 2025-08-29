package PatientMangement.control;
import adt.interfaces.Queue;
import adt.implementations.QueueList;
import adt.interfaces.Collection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import PatientMangement.entity.PatientQueue;

public class QueueCtrl implements Queue<PatientQueue> {
    private static int queueCounter = 2001;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private QueueList<PatientQueue> queueList;

    public QueueCtrl() {
        queueList = new QueueList<>();
    }

    @Override
    public PatientQueue first() {
        return queueList.first();
    }

    @Override
    public PatientQueue last() {
        return queueList.last();
    }

    @Override
    public PatientQueue dequeue() {
        PatientQueue pq = queueList.dequeue();
        if (pq != null) {
            pq.setStatus("In Progress");
        }
        return pq;
    }

    @Override
    public boolean enqueue(PatientQueue patientQueue) {
        // Set queue number
        patientQueue.setQueueNumber(queueCounter++);
        
        // Check for time conflicts
        LocalDateTime proposedTime = patientQueue.getQueueDateTime();
        
        // Create temporary queue for checking times
        QueueList<PatientQueue> tempQueue = new QueueList<>();
        boolean timeExists = false;
        
        while (!queueList.isEmpty()) {
            PatientQueue current = queueList.dequeue();
            if (current.getQueueDateTime().equals(proposedTime)) {
                timeExists = true;
            }
            tempQueue.enqueue(current);
        }
        
        // Restore original queue
        while (!tempQueue.isEmpty()) {
            queueList.enqueue(tempQueue.dequeue());
        }
        
        // If time conflict exists, add one minute
        if (timeExists) {
            patientQueue.setQueueDateTime(proposedTime.plusMinutes(1));
        }
        
        return queueList.enqueue(patientQueue);
    }

    @Override
    public void clear() {
        queueList.clear();
    }

    @Override
    public int count() {
        return queueList.count();
    }

    @Override
    public boolean isEmpty() {
        return queueList.isEmpty();
    }

    @Override
    public Iterator<PatientQueue> iterator() {
        return queueList.iterator();
    }
    
    // Additional methods
    
    public PatientQueue findByPatientId(int patientId) {
        QueueList<PatientQueue> tempQueue = new QueueList<>();
        PatientQueue found = null;
        
        while (!queueList.isEmpty()) {
            PatientQueue current = queueList.dequeue();
            if (current.getPatient().getPatientID() == patientId) {
                found = current;
            }
            tempQueue.enqueue(current);
        }
        
        // Restore queue
        while (!tempQueue.isEmpty()) {
            queueList.enqueue(tempQueue.dequeue());
        }
        
        return found;
    }
    
    public void completePatient(int patientId) {
        PatientQueue pq = findByPatientId(patientId);
        if (pq != null) {
            pq.setStatus("Completed");
        }
    }
}