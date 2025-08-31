package modules.consultation.boundary;

import modules.consultation.control.ConsultationCtrl;
import utils.Context;
import utils.UI;

public class ViewConsultationByPatientUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        var pid = readInt("Enter Patient ID: ", noValidation());
        var list = ConsultationCtrl.getConsultationsByPatient(context, pid);

        println();
        println("Consultations:");

        if (list.isEmpty()) {
            println("No consultations found for " + pid);
        } else {
            for (int i = 0; i < list.size(); i++) {
                println(list.get(i));
            }
        }

        waitInput();

        return null;
    }
}
