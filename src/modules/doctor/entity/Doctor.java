package modules.doctor.entity;

import adt.implementations.ArrayList;
import adt.implementations.ArrayMap;
import adt.interfaces.List;
import adt.interfaces.Map;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Doctor {
    private final String id;
    private String name;
    private int age;
    private String phoneNo;
    private String email;
    private Map<DayOfWeek, Schedule> weeklySchedule = new ArrayMap<>(7);
    private List<TimeSlot> bookedSlots = new ArrayList<>();

    public Doctor(int id, String name, int age, String phoneNo, String email, List<Schedule> schedules) {
        this.id = "D" + id;
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.email = email;

        for (int index = 0; index < schedules.size(); index++) {
            weeklySchedule.insert(DayOfWeek.of(index + 1), schedules.get(index));
        }
    }

    public Doctor(String id, String name, int age, String phoneNo, String email, Map<DayOfWeek, Schedule> weeklySchedule) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.email = email;
        this.weeklySchedule = weeklySchedule;
    }

    public List<TimeSlot> getAvailableSlotsForDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<TimeSlot> timeSlots = weeklySchedule.get(dayOfWeek).generateTimeSlots(date);

        for (int index = 0; index < timeSlots.size(); index++) {
            if (timeSlots.contains(bookedSlots.get(index))) {
                timeSlots.remove(index);
            }
        }

        return timeSlots;
    }

    public void addBookedSlot(TimeSlot timeSlot) {
        bookedSlots.add(timeSlot);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Map<DayOfWeek, Schedule> getWeeklySchedule() {
        return weeklySchedule;
    }

    public List<TimeSlot> getBookedSlots() {
        return bookedSlots;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWeeklySchedule(Map<DayOfWeek, Schedule> weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public void setWeeklySchedule(List<Schedule> schedules) {
        for (int index = 1; index < schedules.size(); index++) {
            weeklySchedule.insert(DayOfWeek.of(index), schedules.get(index));
        }
    }

    public void setBookedSlots(ArrayList<TimeSlot> bookedSlots) {
        this.bookedSlots = bookedSlots;
    }

    public String toString() {
        return "Doctor ID: " + id + " | Name: " + name + " | Age: " + age + " | Phone No: " + phoneNo;
    }
}
