package PatientMangement.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientQueue {
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Properties
    private int queueNumber;
    private Patient patient;
    private LocalDateTime queueDateTime;
    private String status; // e.g., "Waiting", "In Progress", "Completed"
    
    // Constructor
    public PatientQueue(Patient patient) {
        this.patient = patient;
        // Using the current time: 2025-08-29 06:39:28
        this.queueDateTime = LocalDateTime.parse("2025-08-29 06:39:28", DATETIME_FORMATTER);
        this.status = "Waiting";
    }
    
    // Constructor with specific date time
    public PatientQueue(Patient patient, LocalDateTime queueDateTime) {
        this.patient = patient;
        this.queueDateTime = queueDateTime;
        this.status = "Waiting";
    }
    
    // Getters and Setters
    public int getQueueNumber() {
        return queueNumber;
    }
    
    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public LocalDateTime getQueueDateTime() {
        return queueDateTime;
    }
    
    public void setQueueDateTime(LocalDateTime queueDateTime) {
        this.queueDateTime = queueDateTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Format date time to string
    public String getFormattedDateTime() {
        return queueDateTime.format(DATETIME_FORMATTER);
    }
    
    // ToString method for displaying queue information
    @Override
    public String toString() {
        return String.format("Queue Number: %d | Patient ID: %d | Patient Name: %s | DateTime: %s | Status: %s",
            queueNumber,
            patient.getPatientID(),
            patient.getName(),
            getFormattedDateTime(),
            status);
    }
}