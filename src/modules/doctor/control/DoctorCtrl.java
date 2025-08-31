package modules.doctor.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import adt.interfaces.Map;
import modules.doctor.entity.Doctor;
import modules.doctor.entity.Schedule;
import utils.Context;

import java.time.DayOfWeek;

public class DoctorCtrl {
    public static void addDoctor(Context context, String name, int age, String phoneNo, String email, List<Integer> selections) {
        List<Schedule> weeklySchedule = new ArrayList<>(7);

        for (int index = 0; index < 7; index++) {
            switch (selections.get(index)) {
                case 0:
                    weeklySchedule.add(context.noSchedule);
                    break;

                case 1:
                    weeklySchedule.add(context.morningSchedule);
                    break;

                case 2:
                    weeklySchedule.add(context.afternoonSchedule);
                    break;

                case 3:
                    weeklySchedule.add(context.nightSchedule);
                    break;
            }
        }

        Doctor newDoctor = new Doctor(context.doctorCounter.get(), name, age, phoneNo, email, weeklySchedule);
        context.doctors.insert(newDoctor.getId(), newDoctor);
    }

    public static boolean containsDoctorID(Context context, String doctorID) {
        return context.doctors.has(doctorID);
    }

    public static void updateDoctor(Context context, String doctorID, String name, int age, String phoneNo, String email) {
        Map<DayOfWeek, Schedule> weeklySchedule = context.doctors.get(doctorID).getWeeklySchedule();

        Doctor updatedDoctor = new Doctor(doctorID, name, age, phoneNo, email, weeklySchedule);
        context.doctors.remove(doctorID);
        context.doctors.insert(updatedDoctor.getId(), updatedDoctor);
    }

    public static void updateDoctor(Context context, String doctorID, List<Integer> selections) {
        List<Schedule> weeklySchedule = new ArrayList<>(7);

        for (int index = 0; index < 7; index++) {
            switch (selections.get(index)) {
                case 0:
                    weeklySchedule.add(context.noSchedule);
                    break;

                case 1:
                    weeklySchedule.add(context.morningSchedule);
                    break;

                case 2:
                    weeklySchedule.add(context.afternoonSchedule);
                    break;

                case 3:
                    weeklySchedule.add(context.nightSchedule);
                    break;
            }
        }

        Doctor updatedDoctor = context.doctors.get(doctorID);
        updatedDoctor.setWeeklySchedule(weeklySchedule);
        context.doctors.remove(doctorID);
        context.doctors.insert(doctorID, updatedDoctor);
    }


    public static List<Doctor> getAllDoctors(Context context) {
        List<Doctor> doctors = new ArrayList<>();

        for (var entry : context.doctors) {
            doctors.add(entry.value());
        }

        return doctors;
    }
}
