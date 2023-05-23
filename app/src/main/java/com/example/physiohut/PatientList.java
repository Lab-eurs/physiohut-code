package com.example.physiohut;

import java.util.ArrayList;
import java.util.List;

public class PatientList {
    ArrayList<Patient> plist = new ArrayList<Patient>();

    public PatientList(String ip){
        String url = "http://"+ip+"/physiohutDBServices/populateDropDown.php";
        try{
            R8DataFetcher okHttpClient = new R8DataFetcher();
            plist = okHttpClient.populateDropDown(url);
            System.out.println(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getAllPatients(){
        List<String> temp = new ArrayList<String>();
        for(int i=0; i< temp.size();i++){
            temp.add(plist.get(i).getName());
        }
        return temp;
    }
}
