package PatientManagement.boundary;

import PatientManagement.control.QueueCtrl;
import PatientManagement.control.PatientCtrl;  // Note: Different package spelling
import PatientManagement.entity.Patient;
import PatientManagement.entity.PatientQueue;

public class Main {
    public static void main(String[] args) {
        // Create test patients
        Patient patient1 = new Patient("John Doe", 30, "123-456-7890", "johndoe12@gmail.com");
        Patient patient2 = new Patient("Jane Smith", 25, "987-654-3210", "jane3342@gmail.com");
        Patient patient3 = new Patient("Bob Wilson", 45, "555-123-4567", "bob.wilson@email.com");
        
        System.out.println("=== PATIENT CONTROLLER TESTING ===");
        PatientCtrl patientCtrl = new PatientCtrl();
        
        System.out.println("1. Testing PatientCtrl Initialization");
        System.out.println("Initial patient count: " + patientCtrl.getPatientCount());
        
        
        
        System.out.println("\n2. Testing Add Patient Functionality");
        
        // Test adding valid patients
        boolean added1 = patientCtrl.addPatient(patient1);
        System.out.println("Added patient1 (John Doe): " + added1);
        System.out.println("Patient count after adding patient1: " + patientCtrl.getPatientCount());
        
        boolean added2 = patientCtrl.addPatient(patient2);
        System.out.println("Added patient2 (Jane Smith): " + added2);
        System.out.println("Patient count after adding patient2: " + patientCtrl.getPatientCount());
        
        // Test adding null patient
        boolean addedNull = patientCtrl.addPatient(null);
        System.out.println("Added null patient: " + addedNull + " (should be false)");
        
        // Test adding duplicate patient (same ID)
        boolean addedDuplicate = patientCtrl.addPatient(patient1);
        System.out.println("Added duplicate patient1: " + addedDuplicate + " (should be false due to logic issue)");
        
        System.out.println("\n3. Testing Print All Patients");
        patientCtrl.printAllPatients();
        
        System.out.println("\n4. Testing Update Patient Functionality");
        
        // Create updated patient with same ID as patient1
        Patient updatedPatient1 = new Patient("John Doe Updated", 31, "123-456-7890", "john.updated@gmail.com");
        // Note: This creates a new ID, so we need to test with existing ID
        
        boolean updated1 = patientCtrl.updatePatient(patient1.getPatientId(), updatedPatient1);
        System.out.println("Updated patient1: " + updated1);
        
        // Test updating non-existent patient
        boolean updatedNonExistent = patientCtrl.updatePatient(9999, patient3);
        System.out.println("Updated non-existent patient (ID 9999): " + updatedNonExistent + " (should be false)");
        
        System.out.println("\n5. Testing Patient Count");
        System.out.println("Current patient count: " + patientCtrl.getPatientCount());
        
        // Add another patient
        boolean added3 = patientCtrl.addPatient(patient3);
        System.out.println("Added patient3 (Bob Wilson): " + added3);
        System.out.println("Patient count after adding patient3: " + patientCtrl.getPatientCount());
        
        System.out.println("\n6. Final Patient List");
        patientCtrl.printAllPatients();
        
        System.out.println("\n7. Testing Clear All Patients");
        patientCtrl.clearAllPatients();
        System.out.println("Patient count after clearing: " + patientCtrl.getPatientCount());
        
        System.out.println("\n8. Verifying Empty List");
        System.out.println("Patients after clearing:");
        patientCtrl.printAllPatients();
        
        // Test edge cases with cleared registry
        System.out.println("\n9. Testing Operations on Empty Registry");
        boolean addAfterClear = patientCtrl.addPatient(patient1);
        System.out.println("Add patient to empty registry: " + addAfterClear);
        
        boolean updateAfterClear = patientCtrl.updatePatient(patient1.getPatientId(), patient2);
        System.out.println("Update patient in registry with 1 patient: " + updateAfterClear);
        
        System.out.println("Final patient count: " + patientCtrl.getPatientCount());
        
        System.out.println("\n=== PatientCtrl Testing Complete ===");
        
        // Display important notes about the current implementation
        System.out.println("\nIMPORTANT NOTES:");
        System.out.println("- There's a logic issue in addPatient() method");
        System.out.println("- Current logic: returns false if patient doesn't exist (should be opposite)");
        System.out.println("- Recommendation: Fix the condition in addPatient() method");
        System.out.println("- Current: if (patient == null || registry.find(patient.getPatientId()) == null)");
        System.out.println("- Should be: if (patient == null || registry.find(patient.getPatientId()) != null)");
        
        System.out.println("\n=== QUEUE CONTROLLER TESTING ===");
        // Create queue controller
        QueueCtrl queueCtrl = new QueueCtrl();
        
        // Create patient queues
        PatientQueue pq1 = new PatientQueue(patient1);
        PatientQueue pq2 = new PatientQueue(patient2);
        PatientQueue pq3 = new PatientQueue(patient3);
        
        // Add to queue
        queueCtrl.enqueue(pq1);
        queueCtrl.enqueue(pq2);
        queueCtrl.enqueue(pq3);
        
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
        queueCtrl.completePatient(current.getPatient().getPatientId());
    }
}