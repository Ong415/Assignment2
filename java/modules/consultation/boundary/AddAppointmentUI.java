package modules.consultation.boundary;

import modules.consultation.entity.Appointment;
import utils.Context;
import utils.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static modules.consultation.entity.Appointment.isValidDoctorID;
import static modules.consultation.entity.Appointment.isValidPatientID;

public class AddAppointmentUI extends UI<Context> {
    private LocalDateTime dateTime;

    @Override
    protected UI<Context> display() {
        var appointmentID = readString("Enter Appointment ID (e.g. A001): ",
                input -> Appointment.validateID(input.strip()) ? null : "Invalid format. Appointment ID must be like A001.").strip();
        var patientID = readString("Enter Patient ID (P001): ",
                input -> isValidPatientID(input.strip()) ? null : "Invalid format. Patient ID must be like P001.").strip();
        var doctorID = readString("Enter Doctor ID (D001): ",
                input -> isValidDoctorID(input.strip()) ? null : "Invalid format. Doctor ID must be like D001.").strip();

        dateTime = null;

        readString("Enter Date and Time (" + DATE_FORMAT + "): ", input -> {
            try {
                dateTime = LocalDateTime.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                return "Invalid date format. Try again.";
            }
            return null;
        });

        var status = readString("Enter Status: ", noValidation()).strip();

        context.appointments.add(new Appointment(appointmentID, patientID, doctorID, dateTime, status));

        println("Appointment added.");

        waitInput();

        return null;
    }
}
