package utils;

import adt.implementations.ArrayList;
import adt.implementations.HashMap;
import adt.implementations.LinkedQueue;
import adt.interfaces.List;
import adt.interfaces.Map;
import adt.interfaces.Queue;
import modules.consultation.entity.Appointment;
import modules.consultation.entity.Consultation;
import modules.doctor.entity.Doctor;
import modules.doctor.entity.Schedule;
import modules.doctor.entity.TimeSlot;
import modules.patient.control.MaintenanceCtrl;
import modules.patient.entity.Maintenance;
import modules.patient.entity.Patient;
import modules.patient.entity.PatientQueue;
import modules.pharmacy.entity.Medicine;
import modules.treatment.entity.Treatment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    public final Map<String, Doctor> doctors = new HashMap<>();
    public final Counter doctorCounter = new Counter(1000);

    // IDK IF THIS IS CORRECT OR NOT
    public final Schedule morningSchedule = new Schedule(LocalTime.of(7, 0), LocalTime.of(12, 0));
    public final Schedule afternoonSchedule = new Schedule(LocalTime.of(12, 0), LocalTime.of(17, 0));
    public final Schedule nightSchedule = new Schedule(LocalTime.of(17, 0), LocalTime.of(22, 0));
    public final Schedule noSchedule = new Schedule(LocalTime.of(0, 0), LocalTime.of(0, 0));

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

        List<Schedule> d1weeklySchedule = new ArrayList<>(7);
        d1weeklySchedule.add(morningSchedule);
        d1weeklySchedule.add(afternoonSchedule);
        d1weeklySchedule.add(nightSchedule);
        d1weeklySchedule.add(morningSchedule);
        d1weeklySchedule.add(afternoonSchedule);
        d1weeklySchedule.add(noSchedule);
        d1weeklySchedule.add(noSchedule);

        List<Schedule> d2weeklySchedule = new ArrayList<>(7);
        d2weeklySchedule.add(afternoonSchedule);
        d2weeklySchedule.add(nightSchedule);
        d2weeklySchedule.add(morningSchedule);
        d2weeklySchedule.add(afternoonSchedule);
        d2weeklySchedule.add(nightSchedule);
        d2weeklySchedule.add(noSchedule);
        d2weeklySchedule.add(noSchedule);

        List<Schedule> d3weeklySchedule = new ArrayList<>(7);
        d3weeklySchedule.add(nightSchedule);
        d3weeklySchedule.add(morningSchedule);
        d3weeklySchedule.add(afternoonSchedule);
        d3weeklySchedule.add(nightSchedule);
        d3weeklySchedule.add(morningSchedule);
        d3weeklySchedule.add(noSchedule);
        d3weeklySchedule.add(noSchedule);

        Doctor d1 = new Doctor(doctorCounter.get(), "Ben Dover", 28, "011-3468794", "bendover@gmail.com", d1weeklySchedule);
        Doctor d2 = new Doctor(doctorCounter.get(), "Sumting Wong", 35, "016-7249638", "sumtingwong@gmail.com", d2weeklySchedule);
        Doctor d3 = new Doctor(doctorCounter.get(), "Jack Orph", 31, "019-8131987", "jackorph@gmail.com", d3weeklySchedule);

        TimeSlot timeslot = new TimeSlot(LocalDateTime.of(2025, 9, 1, 8, 0), LocalDateTime.of(2025, 9, 1, 9, 0));
        d1.addBookedSlot(timeslot);

        doctors.insert(d1.getId(), d1);
        doctors.insert(d2.getId(), d2);
        doctors.insert(d3.getId(), d3);

        consultations.add(new Consultation("C001", p1, d1, LocalDateTime.of(2025, 8, 24, 12, 45), "", ""));
        consultations.add(new Consultation("C002", p2, d2, LocalDateTime.of(2025, 7, 05, 14, 30), "", ""));
        consultations.add(new Consultation("C003", p3, d3, LocalDateTime.of(2025, 6, 19, 15, 20), "", ""));

        appointments.add(new Appointment("A001", p1, d1, LocalDateTime.of(2025, 8, 24, 12, 00), "Done"));
        appointments.add(new Appointment("A002", p2, d2, LocalDateTime.of(2025, 7, 05, 14, 00), "Done"));
        appointments.add(new Appointment("A003", p3, d3, LocalDateTime.of(2025, 6, 19, 15, 00), "Done"));

        var m1 = new Medicine("M001", "Ibuprofen", 7.2);
        var m2 = new Medicine("M002", "Paracetamol", 4.5);

        medicines.insert(m1.getId(), m1);
        medicines.insert(m2.getId(), m2);

        inventory.insert(m1, 10);
        inventory.insert(m2, 20);

        var tm1 = new HashMap<Medicine, Integer>();

        tm1.insert(m1, 5);

        treatments.add(new Treatment("T001", "fever", tm1, "2025-01-01", d1, p1));
    }
}
