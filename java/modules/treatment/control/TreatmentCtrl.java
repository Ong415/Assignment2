package modules.treatment.control;

import adt.implementations.ArrayList;
import adt.implementations.HashMap;
import adt.interfaces.List;
import adt.interfaces.Map;
import modules.treatment.entity.Treatment;
import utils.Context;

public class TreatmentCtrl {
    public static List<Treatment> getTreatmentsForPatient(Context context, String patientId) {
        var results = new ArrayList<Treatment>();
        for (var t : context.treatments) {
            if (t.getPatientId().equals(patientId)) {
                results.add(t);
            }
        }
        return results;
    }

    public static List<Treatment> getDoctorTreatments(Context context, String doctorId) {
        var results = new ArrayList<Treatment>();
        for (var t : context.treatments) {
            if (t.getDoctorId().equals(doctorId)) {
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

    public static Map<String, Integer> reportPatientTreatments(Context context) {
        Map<String, Integer> counts = new HashMap<>();
        for (Treatment t : context.treatments) {
            String patientId = t.getPatientId();
            int current = counts.has(patientId) ? counts.get(patientId) : 0;
            counts.insert(patientId, current + 1);
        }
        return counts;
    }

    public static Map<String, Integer> reportDoctorTreatments(Context context) {
        Map<String, Integer> counts = new HashMap<>();
        for (Treatment t : context.treatments) {
            String docId = t.getDoctorId();
            int current = counts.has(docId) ? counts.get(docId) : 0;
            counts.insert(docId, current + 1);
        }
        return counts;
    }
}
