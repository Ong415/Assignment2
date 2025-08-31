package modules.doctor.boundary;

import modules.doctor.control.DoctorCtrl;
import modules.doctor.entity.Doctor;
import utils.Context;
import utils.UI;

public class ViewDoctorListUI extends UI<Context> {
    @Override
    protected UI<Context> display() {
        println("===== Doctor List =====");

        var doctors = DoctorCtrl.getAllDoctors(context);
        int i = 1;

        for (Doctor doctor : doctors) {
            println(i + ": " + doctor);
            i++;
        }

        waitInput();
        return null;
    }

}
