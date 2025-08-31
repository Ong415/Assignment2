package modules.patient.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.patient.entity.Maintenance;
import modules.patient.entity.Patient;
import utils.Context;

import java.time.LocalDate;

public class MaintenanceCtrl {
    //Add a new maintenance record for a patient
    public static boolean addMaintenanceRecord(Context context, Patient patient, LocalDate date, String details) {
        if (date == null || details == null || details.trim().isEmpty()) {
            return false;
        }

        try {
            // Create maintenance record
            Maintenance maintenance = new Maintenance(context.maintenanceCounter.get(), patient, date, details);

            context.maintenances.insert(maintenance.getId(), maintenance);

            // Track patient-maintenance relationship using custom HashMap
            List<Integer> patientMaintenanceList = context.patientMaintenanceMap.get(patient);
            if (patientMaintenanceList == null) {
                patientMaintenanceList = new ArrayList<>();
                context.patientMaintenanceMap.insert(patient, patientMaintenanceList);
            }
            patientMaintenanceList.add(maintenance.getId());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Get all maintenance records for a specific patient
    public static List<Maintenance> getMaintenanceRecordsByPatient(Context context, Patient patient) {
        var patientMaintenanceRecords = new ArrayList<Maintenance>();

        var maintenanceIds = context.patientMaintenanceMap.get(patient);
        if (maintenanceIds != null) {
            for (Integer maintenanceId : maintenanceIds) {
                Maintenance maintenance = context.maintenances.get(maintenanceId);
                if (maintenance != null) {
                    patientMaintenanceRecords.add(maintenance);
                }
            }
        }

        return patientMaintenanceRecords;
    }

    //Update an existing maintenance record
    public static boolean updateMaintenanceRecord(Context context, int maintenanceId, LocalDate newDate, String newDetails) {
        Maintenance existingMaintenance = context.maintenances.get(maintenanceId);
        if (existingMaintenance == null) {
            return false;
        }

        if (newDate != null) {
            existingMaintenance.setDate(newDate);
        }
        if (newDetails != null && !newDetails.trim().isEmpty()) {
            existingMaintenance.setDetails(newDetails);
        }

        return true;
    }

    //Get all maintenance records 
    public static List<Maintenance> getAllMaintenanceRecords(Context context) {
        var allRecords = new ArrayList<Maintenance>();
        for (var entry : context.maintenances) {
            allRecords.add(entry.value());
        }
        return allRecords;
    }

    //Get maintenance records within a date range
    public static List<Maintenance> getMaintenanceRecordsByDateRange(Context context, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> recordsInRange = new ArrayList<>();

        for (var entry : context.maintenances) {
            Maintenance maintenance = entry.value();
            LocalDate maintenanceDate = maintenance.getDate();
            if (maintenanceDate != null &&
                    !maintenanceDate.isBefore(startDate) &&
                    !maintenanceDate.isAfter(endDate)) {
                recordsInRange.add(maintenance);
            }
        }

        return recordsInRange;
    }

    // Get maintenance records for a patient within a date range
    public static List<Maintenance> getMaintenanceRecordsByPatientAndDateRange(Context context, Patient patient, LocalDate startDate, LocalDate endDate) {
        var patientRecords = getMaintenanceRecordsByPatient(context, patient);
        var recordsInRange = new ArrayList<Maintenance>();

        for (var maintenance : patientRecords) {
            var maintenanceDate = maintenance.getDate();
            if (maintenanceDate != null &&
                    !maintenanceDate.isBefore(startDate) &&
                    !maintenanceDate.isAfter(endDate)) {
                recordsInRange.add(maintenance);
            }
        }

        return recordsInRange;
    }


}