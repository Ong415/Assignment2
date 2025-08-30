package modules.consultation.boundary;

import modules.consultation.control.ConsultationCtrl;
import modules.consultation.entity.Consultation;
import utils.Context;
import utils.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static modules.consultation.entity.Appointment.isValidDoctorID;
import static modules.consultation.entity.Appointment.isValidPatientID;

public class AddConsultationUI extends UI<Context> {
    private LocalDateTime dateTime;

    @Override
    protected UI<Context> display() {
        var patientID = readString("Enter Patient ID (e.g. P001): ",
                input -> isValidPatientID(input.strip()) ? null : "Invalid format. Patient ID must be like P001.").strip();
        var doctorID = readString("Enter Doctor ID (e.g. D001): ",
                input -> isValidDoctorID(input.strip()) ? null : "Invalid format. Doctor ID must be like D001.").strip();
        var consultationID = readString("Enter Consultation ID (e.g. C001): ",
                input -> Consultation.validateID(input.strip()) ? null : "Invalid format. Consultation ID must be like C001.").strip();

        dateTime = null;

        readString("Enter Date and Time (" + DATE_FORMAT + "): ", input -> {
            try {
                dateTime = LocalDateTime.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                return "Invalid format. Please use " + DATE_FORMAT;
            }

            if (!ConsultationCtrl.validateConsultationTime(context, dateTime))
                return "Invalid time. Must be 8am–6pm, max 5 per day.";
            if (!ConsultationCtrl.isTimeAvailable(context, dateTime))
                return "That time slot is already taken.";
            if (!ConsultationCtrl.isDoctorAvailable(context, doctorID, dateTime))
                return "Doctor is already booked at that time.";

            return null;
        });

        var diagnosis = readString("Enter Diagnosis: ", noValidation()).strip();
        var remarks = readString("Enter Remarks: ", noValidation()).strip();

        context.consultations.add(new Consultation(consultationID, patientID, doctorID, dateTime, diagnosis, remarks));

        println("Consultation added.");

        waitInput();

        return null;
    }
}
