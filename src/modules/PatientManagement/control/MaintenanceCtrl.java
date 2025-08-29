package PatientManagement.control;

import PatientManagement.entity.Maintenance;
import utils.Registry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceCtrl {
    private final Registry<Integer, Maintenance> maintenanceRegistry;
    // Additional registry to track patient-maintenance relationships
    private final Map<Integer, List<Integer>> patientMaintenanceMap;
    
    public MaintenanceCtrl() {
        maintenanceRegistry = new Registry<>();
        patientMaintenanceMap = new Registry<>();
    }
    
    /**
     * Add a new maintenance record for a patient
     * @param patientId The patient's ID
     * @param date The maintenance date
     * @param details The maintenance details
     * @return true if successfully added, false otherwise
     */
    public boolean addMaintenanceRecord(int patientId, LocalDate date, String details) {
        if (date == null || details == null || details.trim().isEmpty()) {
            return false;
        }
        
        try {
            // Create maintenance record
            Maintenance maintenance = new Maintenance(patientId, date, details);
            
            // Register the maintenance record using its key() method
            maintenanceRegistry.register(maintenance);
            
            // Track patient-maintenance relationship
            List<Integer> patientMaintenanceList = patientMaintenanceMap.find(patientId);
            if (patientMaintenanceList == null) {
                patientMaintenanceList = new ArrayList<>();
                // Create a wrapper to make the list identifiable
                PatientMaintenanceList wrapper = new PatientMaintenanceList(patientId, patientMaintenanceList);
                patientMaintenanceMap.register(wrapper);
                patientMaintenanceList = wrapper.getMaintenanceList();
            }
            patientMaintenanceList.add(maintenance.key());
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get all maintenance records for a specific patient
     * @param patientId The patient's ID
     * @return List of maintenance records for the patient
     */
    public List<Maintenance> getMaintenanceRecordsByPatient(int patientId) {
        List<Maintenance> patientMaintenanceRecords = new ArrayList<>();
        
        List<Integer> maintenanceIds = patientMaintenanceMap.find(patientId);
        if (maintenanceIds != null) {
            for (Integer maintenanceId : maintenanceIds) {
                Maintenance maintenance = maintenanceRegistry.find(maintenanceId);
                if (maintenance != null) {
                    patientMaintenanceRecords.add(maintenance);
                }
            }
        }
        
        return patientMaintenanceRecords;
    }
    
    /**
     * Update an existing maintenance record
     * @param maintenanceId The maintenance record ID
     * @param newDate The new date
     * @param newDetails The new details
     * @return true if successfully updated, false if not found
     */
    public boolean updateMaintenanceRecord(int maintenanceId, LocalDate newDate, String newDetails) {
        Maintenance existingMaintenance = maintenanceRegistry.find(maintenanceId);
        if (existingMaintenance == null) {
            return false;
        }
        
        if (newDate != null) {
            existingMaintenance.setDate(newDate);
        }
        if (newDetails != null && !newDetails.trim().isEmpty()) {
            existingMaintenance.setDetails(newDetails); // Using corrected method name
        }
        
        return true;
    }
    
    /**
     * Get a specific maintenance record by ID
     * @param maintenanceId The maintenance record ID
     * @return The maintenance record or null if not found
     */
    public Maintenance getMaintenanceRecord(int maintenanceId) {
        return maintenanceRegistry.find(maintenanceId);
    }
    
    /**
     * Get all maintenance records
     * @return List of all maintenance records
     */
    public List<Maintenance> getAllMaintenanceRecords() {
        List<Maintenance> allRecords = new ArrayList<>();
        for (Maintenance maintenance : maintenanceRegistry) {
            allRecords.add(maintenance);
        }
        return allRecords;
    }
    
    /**
     * Print all maintenance records
     */
    public void printAllMaintenanceRecords() {
        System.out.println("=== All Maintenance Records ===");
        if (maintenanceRegistry.size() == 0) {
            System.out.println("No maintenance records found.");
        } else {
            for (Maintenance maintenance : maintenanceRegistry) {
                System.out.println(maintenance);
            }
        }
    }
    
    /**
     * Print maintenance records for a specific patient
     * @param patientId The patient's ID
     */
    public void printMaintenanceRecordsByPatient(int patientId) {
        System.out.println("=== Maintenance Records for Patient ID: " + patientId + " ===");
        List<Maintenance> patientRecords = getMaintenanceRecordsByPatient(patientId);
        
        if (patientRecords.isEmpty()) {
            System.out.println("No maintenance records found for this patient.");
        } else {
            for (Maintenance maintenance : patientRecords) {
                System.out.println(maintenance);
            }
        }
    }
    
    /**
     * Get maintenance records within a date range
     * @param startDate The start date
     * @param endDate The end date
     * @return List of maintenance records within the date range
     */
    public List<Maintenance> getMaintenanceRecordsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Maintenance> recordsInRange = new ArrayList<>();
        
        for (Maintenance maintenance : maintenanceRegistry) {
            LocalDate maintenanceDate = maintenance.getDate();
            if (maintenanceDate != null && 
                !maintenanceDate.isBefore(startDate) && 
                !maintenanceDate.isAfter(endDate)) {
                recordsInRange.add(maintenance);
            }
        }
        
        return recordsInRange;
    }
    
    /**
     * Get maintenance records for a patient within a date range
     * @param patientId The patient's ID
     * @param startDate The start date
     * @param endDate The end date
     * @return List of maintenance records for the patient within the date range
     */
    public List<Maintenance> getMaintenanceRecordsByPatientAndDateRange(int patientId, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> patientRecords = getMaintenanceRecordsByPatient(patientId);
        List<Maintenance> recordsInRange = new ArrayList<>();
        
        for (Maintenance maintenance : patientRecords) {
            LocalDate maintenanceDate = maintenance.getDate();
            if (maintenanceDate != null && 
                !maintenanceDate.isBefore(startDate) && 
                !maintenanceDate.isAfter(endDate)) {
                recordsInRange.add(maintenance);
            }
        }
        
        return recordsInRange;
    }
    
    /**
     * Get the total count of maintenance records
     * @return The number of maintenance records
     */
    public int getMaintenanceRecordCount() {
        return maintenanceRegistry.size();
    }
    
    /**
     * Get the count of maintenance records for a specific patient
     * @param patientId The patient's ID
     * @return The number of maintenance records for the patient
     */
    public int getMaintenanceRecordCountByPatient(int patientId) {
        return getMaintenanceRecordsByPatient(patientId).size();
    }
    
    /**
     * Clear all maintenance records
     */
    public void clearAllMaintenanceRecords() {
        maintenanceRegistry.clear();
        patientMaintenanceMap.clear();
    }
    
    /**
     * Remove maintenance records for a specific patient
     * @param patientId The patient's ID
     * @return true if records were found and removed, false otherwise
     */
    public boolean removeMaintenanceRecordsByPatient(int patientId) {
        List<Integer> maintenanceIds = patientMaintenanceMap.find(patientId);
        if (maintenanceIds == null || maintenanceIds.isEmpty()) {
            return false;
        }
        
        // Note: Since Registry doesn't have remove method, we can't actually remove
        // But we can clear the patient's maintenance list
        maintenanceIds.clear();
        return true;
    }
    
    // Helper class to make patient maintenance lists identifiable
    private static class PatientMaintenanceList implements Registry.Identifiable<Integer> {
        private final Integer patientId;
        private final List<Integer> maintenanceList;
        
        public PatientMaintenanceList(Integer patientId, List<Integer> maintenanceList) {
            this.patientId = patientId;
            this.maintenanceList = maintenanceList;
        }
        
        @Override
        public Integer key() {
            return patientId;
        }
        
        public List<Integer> getMaintenanceList() {
            return maintenanceList;
        }
    }
}