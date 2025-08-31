package utils;

import adt.implementations.ArrayList;
import adt.implementations.HashMap;
import adt.implementations.LinkedQueue;
import adt.interfaces.List;
import adt.interfaces.Map;
import adt.interfaces.Queue;
import modules.consultation.entity.Appointment;
import modules.consultation.entity.Consultation;
import modules.patient.control.MaintenanceCtrl;
import modules.patient.entity.Maintenance;
import modules.patient.entity.Patient;
import modules.patient.entity.PatientQueue;
import modules.pharmacy.entity.Medicine;
import modules.treatment.entity.Treatment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Context {
    public final Map<String, Medicine> medicines = new HashMap<>();
    public final Map<Medicine, Integer> inventory = new HashMap<>();
    public final List<Treatment> treatments = new ArrayList<>();
    public final List<Consultation> consultations = new ArrayList<>();
    public final List<Appointment> appointments = new ArrayList<>();
    public final Map<Integer, Patient> patients = new HashMap<>();
    public final Map<Integer, Maintenance> maintenances = new HashMap<>();
    public final Counter maintenanceCounter = new Counter(2000);
    public final Counter patientCounter = new Counter(1000);
    public final Counter queueCounter = new Counter(4000);
    public final Queue<PatientQueue> queue = new LinkedQueue<>();
    public final Map<Patient, List<Integer>> patientMaintenanceMap = new HashMap<>();

    public Context() {
        Patient p1 = new Patient(patientCounter.get(), "David", 34, "012-7763343", "david223@gmail.com");
        Patient p2 = new Patient(patientCounter.get(), "Sally", 25, "016-8172654", "sally2103@gmail.com");
        Patient p3 = new Patient(patientCounter.get(), "Micheal", 56, "011-78864443", "mic5152@gmail.com");

        patients.insert(p1.getId(), p1);
        patients.insert(p2.getId(), p2);
        patients.insert(p3.getId(), p3);

        MaintenanceCtrl.addMaintenanceRecord(this, p1, LocalDate.of(2024, 1, 23), "Annual Checkup: \n Status: Good");
        MaintenanceCtrl.addMaintenanceRecord(this, p2, LocalDate.of(2024, 6, 17), "Illness: Catch a cold.");
        MaintenanceCtrl.addMaintenanceRecord(this, p3, LocalDate.of(2025, 1, 8), "Illness: Fever");
        MaintenanceCtrl.addMaintenanceRecord(this, p1, LocalDate.of(2025, 1, 30), "Annual Checkup: \n Status: Good");

        consultations.add(new Consultation("C001", p1, "D001", LocalDateTime.of(2025, 8, 24, 12, 45), "", ""));
        consultations.add(new Consultation("C002", p2, "D002", LocalDateTime.of(2025, 7, 05, 14, 30), "", ""));
        consultations.add(new Consultation("C003", p3, "D003", LocalDateTime.of(2025, 6, 19, 15, 20), "", ""));

        appointments.add(new Appointment("A001", p1, "D001", LocalDateTime.of(2025, 8, 24, 12, 00), "Done"));
        appointments.add(new Appointment("A002", p2, "D002", LocalDateTime.of(2025, 7, 05, 14, 00), "Done"));
        appointments.add(new Appointment("A003", p3, "D003", LocalDateTime.of(2025, 6, 19, 15, 00), "Done"));

        var m1 = new Medicine("M001", "Ibuprofen", 7.2);
        var m2 = new Medicine("M002", "Paracetamol", 4.5);

        medicines.insert(m1.getId(), m1);
        medicines.insert(m2.getId(), m2);

        inventory.insert(m1, 10);
        inventory.insert(m2, 20);

        var tm1 = new HashMap<Medicine, Integer>();

        tm1.insert(m1, 5);

        treatments.add(new Treatment("T001", "fever", tm1, "2025-01-01", "D000", p1));
    }
}
