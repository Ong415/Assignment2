package modules.patient.boundary;

import java.time.LocalDateTime;

import modules.patient.control.QueueCtrl;
import modules.patient.entity.PatientQueue;
import utils.Context;
import utils.UI;

public class GetQueueUI extends UI<Context>{
    private LocalDateTime now;

    @Override
    protected UI<Context> display() {
        int patientId = readInt("Enter Patient ID: ",input -> context.patients.find(input) == null ? "Patient not existed": null);
        now = LocalDateTime.now();
        QueueCtrl.enqueue(context, new PatientQueue(context.patients.find(patientId), now));

        var queueList = QueueCtrl.getAllQueueList(context);
        for (PatientQueue queue : queueList){
            println(queue);
        }
        
        waitInput();
        return null;
    }
}
