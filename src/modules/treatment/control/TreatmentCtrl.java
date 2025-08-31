package modules.treatment.control;

import adt.implementations.ArrayList;
import adt.implementations.HashMap;
import adt.interfaces.List;
import adt.interfaces.Map;
import modules.doctor.entity.Doctor;
import modules.patient.entity.Patient;
import modules.treatment.entity.Treatment;
import utils.Context;

public class TreatmentCtrl {
    public static List<Treatment> getTreatmentsForPatient(Context context, Patient patient) {
        var results = new ArrayList<Treatment>();
        for (var t : context.treatments) {
            if (t.getPatient() == patient) {
                results.add(t);
            }
        }
        return results;
    }

    public static List<Treatment> getDoctorTreatments(Context context, Doctor doctor) {
        var results = new ArrayList<Treatment>();
        for (var t : context.treatments) {
            if (t.getDoctor() == doctor) {
                results.add(t);
            }
        }
        return results;
    }

    public static Treatment removeTreatment(Context context, String id) {
        for (int i = 0; i < context.treatments.size(); i++) {
            if (context.treatments.get(i).getId().equals(id)) {
                return context.treatments.remove(i);
            }
        }
        return null;
    }

    public static Map<Patient, Integer> reportPatientTreatments(Context context) {
        Map<Patient, Integer> counts = new HashMap<>();
        for (Treatment t : context.treatments) {
            var patient = t.getPatient();
            int current = counts.has(patient) ? counts.get(patient) : 0;
            counts.insert(patient, current + 1);
        }
        return counts;
    }

    public static Map<Doctor, Integer> reportDoctorTreatments(Context context) {
        Map<Doctor, Integer> counts = new HashMap<>();
        for (Treatment t : context.treatments) {
            var doc = t.getDoctor();
            int current = counts.has(doc) ? counts.get(doc) : 0;
            counts.insert(doc, current + 1);
        }
        return counts;
    }
}
