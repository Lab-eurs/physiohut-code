package com.example.physiohut;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsList {

    ArrayList<Appointments> cbList = new ArrayList<Appointments>();

    public AppointmentsList(String ip) {
        String url= "http://"+ip+"/PhysiohutDB/populateDB.php";
        try {
            R6FetchData r6FetchData = new R6FetchData();
            cbList = r6FetchData.populateDropDown(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getNames(){
        List<String> temp = new ArrayList<String>();
        for(int i=0; i< temp.size();i++){
            temp.add(cbList.get(i).getName());
        }
        return temp;
    }
}
