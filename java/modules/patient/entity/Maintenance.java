package modules.patient.entity;

import utils.Registry;

import java.time.LocalDate;

public class Maintenance implements Registry.Identifiable<Integer> {
    private final int id;
    private final Patient patient;
    private LocalDate date;
    private String details;

    public Maintenance(int id, Patient patient, LocalDate date, String details) {
        this.patient = patient;
        this.id = id;
        this.date = date;
        this.details = details;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDetails() {
        return this.details;
    }

    public String toString() {
        return "Maintenance ID: " + this.id + " |Patient: "+ this.patient.getId() + " |Date: " + this.date + " |Details: " + this.details;
    }

    @Override
    public Integer key() {
        return id;
    }
}
