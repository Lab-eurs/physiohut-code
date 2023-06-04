package com.example.physiohut.R6;

import com.example.physiohut.model.Appointments;
import com.example.physiohut.NetworkConstants;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsList {

    ArrayList<Appointments> cbList = new ArrayList<Appointments>();

    public AppointmentsList(int patientID) {
        String url = NetworkConstants.getUrlOfFile("r6-r10-get_patient_provisions.php") + "?patient_id=" + patientID;
        try {
            R6DataFetcher r6DataFetcher = new R6DataFetcher();
            cbList = r6DataFetcher.fetchWeeklyAppointments(url);
            //filter them for this week
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Appointments> getAppointmentList() {
        return cbList;
    }

    public List<String> getNames(){
        List<String> temp = new ArrayList<String>();
        for(int i=0; i< cbList.size();i++){
            temp.add(cbList.get(i).getName());
        }
        return temp;
    }
}
