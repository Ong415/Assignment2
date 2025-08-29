package PatientMangement.boundary;
import PatientMangement.control.QueueCtrl;
import PatientMangement.entity.Patient;
import PatientMangement.entity.PatientQueue;
import adt.implementations.*;
import adt.interfaces.*;

public class Main {
    public static void main(String[] args) {
     // Create queue controller
        QueueCtrl queueCtrl = new QueueCtrl();
        
        // Create patients
        Patient patient1 = new Patient("John Doe", 30, "123-456-7890");
        Patient patient2 = new Patient("Jane Smith", 25, "987-654-3210");
        
        // Create patient queues
        PatientQueue pq1 = new PatientQueue(patient1);
        PatientQueue pq2 = new PatientQueue(patient2);
        
        // Add to queue
        queueCtrl.enqueue(pq1);
        queueCtrl.enqueue(pq2);
        
        // Display queue information
        System.out.println("Current Queue:");
        for (PatientQueue pq : queueCtrl) {
            System.out.println(pq);
        }
        
        // Process first patient
        PatientQueue current = queueCtrl.dequeue();
        System.out.println("\nProcessing patient:");
        System.out.println(current);
        
        // Complete patient
        queueCtrl.completePatient(current.getPatient().getPatientID());
    }
}