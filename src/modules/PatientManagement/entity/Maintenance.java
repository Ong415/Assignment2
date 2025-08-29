package PatientManagement.entity;
import utils.Registry;
import java.time.LocalDate;

public class Maintenance implements Registry.Identifiable<Integer> {
    private static int maintenanceSequence = 001;
    private int patientId;
    private int maintenanceId;
    private LocalDate date;
    private String details;

    public Maintenance(int patientId, LocalDate date, String details){
        this.maintenanceId = maintenanceSequence;
        this.patientId = patientId;
        this.date = date;
        this.details = details;

        maintenanceSequence++;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public int getMaintenanceId(){
        return this.maintenanceId;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getDetails(){
        return this.details;
    }

    public String toString(){
        return "Maintenance ID: " + this.maintenanceId + " |Date: " + this.date + " |Detials: " + this.details;
    }

    @Override
    public Integer key() {
        return maintenanceId;
    }
}
