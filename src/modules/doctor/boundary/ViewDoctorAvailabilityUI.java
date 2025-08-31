package modules.doctor.boundary;

import adt.interfaces.List;
import modules.doctor.control.DoctorCtrl;
import modules.doctor.entity.TimeSlot;
import utils.Context;
import utils.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ViewDoctorAvailabilityUI extends UI<Context> {
    private LocalDate date;

    @Override
    protected UI<Context> display() {
        println("===== Doctor Availability =====");
        String doctorId = readString("Enter Doctor ID (eg D1000): ", noValidation()).toUpperCase();

        if (DoctorCtrl.containsDoctorID(context, doctorId)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = null;

            readString("Enter Date (yyyy-MM-dd): ", input -> {
                try {
                    date = LocalDate.parse(input, formatter);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Try again.";
                }

                return null;
            });

            clear();
            println("===== Doctor Availability =====");
            println("Doctor: " + context.doctors.get(doctorId).getName());
            println();
            println("Date: " + date + " (" + date.getDayOfWeek() + ")");

            List<TimeSlot> availableTimeSlots = context.doctors.get(doctorId).getAvailableSlotsForDate(date);
            List<TimeSlot> totalTimeSlots = context.doctors.get(doctorId).getWeeklySchedule().entry(date.getDayOfWeek()).value().generateTimeSlots(date);

            for (var timeSlot : totalTimeSlots) {
                print(timeSlot.getStartDateTime().toLocalTime() + " - " + timeSlot.getEndDateTime().toLocalTime() + ": ");

                if (availableTimeSlots.contains(timeSlot)) {
                    println("Available");
                } else {
                    println("Booked");
                }
            }

            if (availableTimeSlots.isEmpty()) {
                println("Not Available");
            }
        } else {
            println("Doctor ID Doesn't Exist!");
        }

        waitInput();
        return null;
    }
}
