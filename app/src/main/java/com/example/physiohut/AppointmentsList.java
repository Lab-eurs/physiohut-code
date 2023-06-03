package com.example.physiohut;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsList {

    ArrayList<Appointments> cbList = new ArrayList<Appointments>();

    public AppointmentsList(String ip) {
        String url= "http://"+ip+"/PhysiohutDB/get_user_name.php";
        try {
            R6FetchData r6FetchData = new R6FetchData();
            cbList = r6FetchData.populateDropDown(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getNames(){
        List<String> temp = new ArrayList<String>();
        for(int i=0; i< cbList.size();i++){
            temp.add(cbList.get(i).getName());
        }
        return temp;
    }
}
