package modules.doctor.entity;


import adt.implementations.ArrayList;
import adt.interfaces.List;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<TimeSlot> generateTimeSlots(LocalDate date) {
        int hours = (int) (Duration.between(startTime, endTime)).toHours();

        List<TimeSlot> timeSlots = new ArrayList<>(hours);
        LocalTime current = startTime;

        while (current.isBefore(endTime)) {
            LocalTime next = current.plusHours(1);

            if (next.isAfter(endTime)) {
                next = endTime;
            }

            timeSlots.add(new TimeSlot(current.atDate(date), next.atDate(date)));

            current = next;
        }

        return timeSlots;
    }

    // Getters
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    // Setters
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return "startTime: " + startTime + " | endTime: " + endTime;
    }
}
