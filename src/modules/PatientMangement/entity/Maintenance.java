package PatientMangement.entity;
import java.time.LocalDate;

public class Maintenance {
    private static int maintenanceSequence = 001;
    private int maintenanceID;
    private LocalDate date;
    private String details;

    Maintenance(int maintenanceID, LocalDate date, String details){
        this.maintenanceID = maintenanceSequence;
        this.date = date;
        this.details = details;

        maintenanceSequence++;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setDetials(String details){
        this.details = details;
    }

    public int getMaintenanceId(){
        return this.maintenanceID;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getDetials(){
        return this.details;
    }

    public String toString(){
        return "Maintenance ID: " + this.maintenanceID + "Date: " + this.date + "Detials: " + this.details;
    }
}
