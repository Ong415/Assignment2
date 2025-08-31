package modules.doctor.entity;

import java.time.LocalDateTime;

public class TimeSlot {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeSlot(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
    }

    // Getters
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // Setters
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String toString() {
        return "startTime: " + startDateTime + " | endTime: " + endDateTime;
    }
}