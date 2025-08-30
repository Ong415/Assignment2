/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.patient.boundary;
import adt.implementations.HashMap;
import utils.Context;
import utils.UI;

public class PatientUI extends UI<Context>{
    @Override
    protected UI<Context> display() {
        println ("1. Register Patient Information");
        println ("2. Update Patient Information");
        println ("3. Display Patient Information");
        println ("4. Create New Maintenance Record");
        println ("5. Update Maintenance Record");
        println ("6. Check Maintenance Record");
        println ("7. Get Queue Number");
        println ("0. Exit");
        
        var choice = new HashMap<Character,UI<Context>>();
        
        choice.insert('1', new PatientInformationUI(true));
        choice.insert('2', new PatientInformationUI(false));
        choice.insert('3', new DisplayPatientUI());
        choice.insert('4', new CreateMaintenanceUI(true));
        choice.insert('5', new CreateMaintenanceUI(false));
        choice.insert('6', new CheckMaintenanceUI());
        choice.insert('7', new GetQueueUI());
        
        choice.insert('0', null);
        
        return readOption("Enter: ", choice);
    }
}
