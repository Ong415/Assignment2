package modules.patient.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.patient.entity.Patient;
import utils.Context;

public class PatientCtrl {
    public static boolean addPatient(Context context, Patient patient) {
        if (patient == null || context.patients.get(patient.getId()) != null) return false;
        context.patients.insert(patient.getId(),patient);
        return true;
    }

    public static boolean updatePatient(Context context, int id, Patient updated) {
        if (context.patients.get(id) == null) return false;

        context.patients.insert(id,updated); // Replace existing
        return true;
    }

    public static List<Patient> getAllPatients(Context context) {
        var list = new ArrayList<Patient>();
        for (var entry : context.patients) {
            list.add(entry.value());
        }
        return list;
    }
}
