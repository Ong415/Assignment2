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
import modules.patient.control.QueueCtrl;
import modules.patient.entity.Maintenance;
import modules.patient.entity.Patient;
import modules.patient.entity.PatientQueue;
import modules.pharmacy.entity.Medicine;
import modules.treatment.entity.Treatment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Context {
    public final Registry<String, Medicine> medicines = new Registry<>();
    public final Map<Medicine, Integer> inventory = new HashMap<>();
    public final List<Treatment> treatments = new ArrayList<>();
    public final List<Consultation> consultations = new ArrayList<>();
    public final List<Appointment> appointments = new ArrayList<>();
    public final Registry<Integer, Patient> patients = new Registry<>();
    public final Registry<Integer, Maintenance> maintenances = new Registry<>();
    public final Counter maintenanceCounter = new Counter(2000);
    public final Counter patientCounter = new Counter(1000);
    public final Counter queueCounter = new Counter(4000);
    public final Queue<PatientQueue> queue = new LinkedQueue<>();
    public final Map<Patient, List<Integer>> patientMaintenanceMap = new HashMap<>();

    public Context() {
        patients.register(new Patient(patientCounter.get(),"David",34,"012-7763343","david223@gmail.com"));
        patients.register(new Patient(patientCounter.get(),"Sally",25,"016-8172654","sally2103@gmail.com"));
        patients.register(new Patient(patientCounter.get(),"Micheal",56,"011-78864443","mic5152@gmail.com"));

        Patient p1 = patients.find(1000);
        Patient p2 = patients.find(1001);
        Patient p3 = patients.find(1002);
        MaintenanceCtrl.addMaintenanceRecord(this, p1, LocalDate.of(2024, 1, 23), "Annual Checkup: \n Status: Good");
        MaintenanceCtrl.addMaintenanceRecord(this, p2, LocalDate.of(2024, 6, 17), "Illness: Catch a cold.");
        MaintenanceCtrl.addMaintenanceRecord(this, p3, LocalDate.of(2025, 1, 8), "Illness: Fever");
        MaintenanceCtrl.addMaintenanceRecord(this, p1, LocalDate.of(2025, 1, 30), "Annual Checkup: \n Status: Good");

        QueueCtrl.enqueue(this,new PatientQueue(p3));
        QueueCtrl.enqueue(this,new PatientQueue(p1, LocalDateTime.of(2025,8,29,13,17,30)));
        QueueCtrl.enqueue(this,new PatientQueue(p2, LocalDateTime.of(2025,8,29,14,25,27)));

        consultations.add(new Consultation("C001", "P001", "D001" , LocalDateTime.of(2025, 8, 24, 12, 45), "", ""));
        consultations.add(new Consultation("C002", "P002", "D002" , LocalDateTime.of(2025, 7, 05, 14, 30), "", ""));
        consultations.add(new Consultation("C003", "P003", "D003" , LocalDateTime.of(2025, 6, 19, 15, 20), "", ""));

        appointments.add(new Appointment("A001", "P001", "D001" , LocalDateTime.of(2025, 8, 24, 12, 00), "Done"));
        appointments.add(new Appointment("A002", "P002", "D002" , LocalDateTime.of(2025, 7, 05, 14, 00), "Done"));
        appointments.add(new Appointment("A003", "P003", "D003" , LocalDateTime.of(2025, 6, 19, 15, 00), "Done"));
    }
}
