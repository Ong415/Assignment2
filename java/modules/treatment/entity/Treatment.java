package modules.treatment.entity;

import adt.interfaces.Map;
import modules.pharmacy.entity.Medicine;

public class Treatment {
    private String id;
    private String diagnosis;
    private Map<Medicine, Integer> medication;
    private String date;
    private String doctorId;
    private String patientId;

    public Treatment(String id, String diagnosis, Map<Medicine, Integer> medication, String date, String doctorId, String patientId) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Map<Medicine, Integer> getMedication() {
        return medication;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Treatment{" + "treatmentId=" + id + ", diagnosis=" + diagnosis + ", medication=" + medication + ", date=" + date + ", doctorId=" + doctorId + ", patientId=" + patientId + '}';
    }
}
