package com.example.physiohut;

import java.util.ArrayList;
import java.util.List;

public class PatientList {
    ArrayList<Patient> plist = new ArrayList<Patient>();
    public PatientList(String ip){
        String url = "http://"+ip+"/physiohutDBServices/populateDropDown.php";
        try{
            R8DataFetcher r8DataFetcher = new R8DataFetcher();
            plist = r8DataFetcher.populateDropDown(url);
            System.out.println(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getAllPatients(){
        List<String> temp = new ArrayList<String>();
        for(int i=0; i< plist.size();i++){
            System.out.println(plist.get(i).getName());
            temp.add(plist.get(i).getName());
        }
        return temp;
    }
}