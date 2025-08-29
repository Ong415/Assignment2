package consultation.boundary;

import consultation.control.ConsultationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static consultation.entity.Appointment.*;
import static consultation.entity.Consultation.isValidConsultationID;

import consultation.entity.*;

public class ConsultationUI {
    private ConsultationManager manager = new ConsultationManager();
    private Scanner sc = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void start() {
        int choice;
        do {
            System.out.println("\n=== Consultation System Menu ===");
            System.out.println("1. Add Consultation");
            System.out.println("2. Add Appointment");
            System.out.println("3. View Consultations by Patient");
            System.out.println("4. View Appointments by Patient");
            System.out.println("5. View Appointments by Doctor");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addConsultation();
                case 2 -> addAppointment();
                case 3 -> viewConsultationsByPatient();
                case 4 -> viewAppointmentsByPatient();
                case 5 -> viewAppointmentsByDoctor();
                case 0 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ---------------- Add Consultation ----------------
    private void addConsultation() {
        String patientID;
        do {
            System.out.print("Enter Patient ID (P001): ");
            patientID = sc.nextLine().trim();
            if (!isValidPatientID(patientID)) {
                System.out.println("Invalid format. Patient ID must be like p001.");
            }
        } while (!isValidPatientID(patientID));

        String doctorID;
        do {
            System.out.print("Enter Doctor ID (D001): ");
            doctorID = sc.nextLine().trim();
            if (!isValidDoctorID(doctorID)) {
                System.out.println("Invalid format. Patient ID must be like D001.");
            }
        } while(!isValidDoctorID(doctorID));

        String consultationID;
        do {
            System.out.print("Enter Consultation ID (C001): ");
            consultationID = sc.nextLine().trim();
            
            if (!isValidConsultationID(consultationID)) {
                System.out.println("Invalid format. Patient ID must be like C001.");
            }
        } while (!isValidConsultationID(consultationID));

        LocalDateTime dateTime = null;
        boolean validTime = false;
        while (!validTime) {
            try {
                System.out.print("Enter Date and Time (yyyy-MM-dd HH:mm): ");
                String dtStr = sc.nextLine().trim();
                dateTime = LocalDateTime.parse(dtStr, formatter);

                if (!manager.validateConsultationTime(dateTime)) {
                    System.out.println("Invalid time. Must be 8am–6pm, max 5 per day.");
                    continue;
                }
                if (!manager.isTimeAvailable(dateTime)) {
                    System.out.println("That time slot is already taken.");
                    continue;
                }
                if (!manager.isDoctorAvailable(doctorID, dateTime)) {
                    System.out.println("Doctor is already booked at that time.");
                    continue;
                }
                validTime = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use yyyy-MM-dd HH:mm.");
            }
        }

        System.out.print("Enter Diagnosis: ");
        String diagnosis = sc.nextLine().trim();
        System.out.print("Enter Remarks: ");
        String remarks = sc.nextLine().trim();

        Consultation c = new Consultation(consultationID, patientID, doctorID, dateTime, diagnosis, remarks);
        manager.addConsultation(c);
        System.out.println("Consultation added.");
    }

    // ---------------- Add Appointment ----------------
    private void addAppointment() {
        String appointmentID;
        do {
            System.out.print("Enter Appointment ID (A001): ");
            appointmentID = sc.nextLine().trim();
            if (!isValidAppointmentID(appointmentID)) {
                System.out.println("Invalid format. Patient ID must be like C001.");
            }
        } while (!isValidAppointmentID(appointmentID));

        String patientID;
        do {
            System.out.print("Enter Patient ID (P001): ");
            patientID = sc.nextLine().trim();
        } while (!isValidPatientID(patientID));

        String doctorID;
        do {
            System.out.print("Enter Doctor ID (D001): ");
            doctorID = sc.nextLine().trim();
            if (!isValidDoctorID(doctorID)) {
                System.out.println("Invalid format. Patient ID must be like C001.");
            }
        } while (!isValidDoctorID(doctorID));

        LocalDateTime dateTime = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Enter Date and Time (yyyy-MM-dd HH:mm): ");
                String dtStr = sc.nextLine().trim();
                dateTime = LocalDateTime.parse(dtStr, formatter);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        }

        System.out.print("Enter Status: ");
        String status = sc.nextLine().trim();

        Appointment a = new Appointment(appointmentID, patientID, doctorID, dateTime, status);
        manager.addAppointment(a);
        System.out.println("Appointment added.");
    }

    // ---------------- View Methods ----------------
    private void viewConsultationsByPatient() {
        System.out.print("Enter Patient ID: ");
        String pid = sc.nextLine();
        var list = manager.getConsultationsByPatient(pid);
        System.out.println("\nConsultations:");
        if (list.size() == 0) {
            System.out.println("No consultations found for " + pid);
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

    private void viewAppointmentsByPatient() {
        System.out.print("Enter Patient ID: ");
        String pid = sc.nextLine();
        var list = manager.getAppointmentsByPatient(pid);
        System.out.println("\nAppointments:");
        if (list.size() == 0) {
            System.out.println("No appointments found for " + pid);
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

    private void viewAppointmentsByDoctor() {
        System.out.print("Enter Doctor ID: ");
        String did = sc.nextLine();
        var list = manager.getAppointmentsByDoctor(did);
        System.out.println("\nAppointments:");
        if (list.size() == 0) {
            System.out.println("No appointments found for " + did);
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
