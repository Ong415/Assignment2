package modules.patient.control;

import adt.implementations.ArrayList;
import adt.interfaces.List;
import modules.patient.entity.Patient;
import utils.Context;

public class PatientCtrl {
    public static boolean addPatient(Context context, Patient patient) {
        if (patient == null || context.patients.find(patient.getId()) != null) return false;
        context.patients.register(patient);
        return true;
    }

    public static boolean updatePatient(Context context, int id, Patient updated) {
        if (context.patients.find(id) == null) return false;

        context.patients.register(updated); // Replace existing
        return true;
    }

    public static List<Patient> getAllPatients(Context context) {
        var list = new ArrayList<Patient>();
        for (var p : context.patients) {
            list.add(p);
        }
        return list;
    }
}
