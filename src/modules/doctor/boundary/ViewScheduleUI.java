package modules.doctor.boundary;

import modules.doctor.control.DoctorCtrl;
import modules.doctor.entity.Doctor;
import utils.Context;
import utils.UI;

import java.time.LocalTime;

public class ViewScheduleUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("===== Doctor Schedule =====");
        String doctorId = readString("Enter Doctor ID (eg D1000): ", noValidation()).toUpperCase();

        if (DoctorCtrl.containsDoctorID(context, doctorId)) {
            Doctor doctor = context.doctors.get(doctorId);

            clear();
            println("===== Doctor Schedule =====");
            println("Doctor: " + doctor.getName());
            println();

            for (var entry : doctor.getWeeklySchedule()) {
                if (entry.value().getEndTime() != LocalTime.of(0, 0)) {
                    println(entry.key() + ": " + entry.value().getStartTime() + " - " + entry.value().getEndTime());
                } else {
                    println(entry.key() + ": -");
                }
            }
        } else {
            println("Doctor ID Doesn't Exist!");
        }

        waitInput();
        return null;
    }
}
