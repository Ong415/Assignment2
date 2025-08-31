package modules.treatment.entity;

import adt.interfaces.Map;
import modules.patient.entity.Patient;
import modules.pharmacy.entity.Medicine;

public class Treatment {
    private String id;
    private String diagnosis;
    private final Map<Medicine, Integer> medication;
    private String date;
    private String doctorId;
    private final Patient patient;

    public Treatment(String id, String diagnosis, Map<Medicine, Integer> medication, String date, String doctorId, Patient patient) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.date = date;
        this.doctorId = doctorId;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Treatment{" + "treatmentId=" + id + ", diagnosis=" + diagnosis + ", medication=" + medication + ", date=" + date + ", doctorId=" + doctorId + ", patientId=" + patient.getId() + '}';
    }
}
