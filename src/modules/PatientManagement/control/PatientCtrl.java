package PatientManagement.control;


import PatientManagement.entity.Patient;
import utils.Registry;

public class PatientCtrl {
    private final Registry<Integer, Patient> registry;

    public PatientCtrl() {
        registry = new Registry<>();
    }

    public boolean addPatient(Patient patient) {
        if (patient == null || registry.find(patient.getPatientId()) == null) return false;
        registry.register(patient);
        return true;
    }


    public boolean updatePatient(int id, Patient updated) {
        if (registry.find(id) == null) return false;

        registry.register(updated); // Replace existing
        return true;
    }

    public void printAllPatients() {
        for (Patient p : registry) {
            System.out.println(p);
        }
    }

    //Get Patient map size
    public int getPatientCount() {
        return registry.size();
    }

    //Clear Patient map size
    public void clearAllPatients() {
        registry.clear();
    }
}
