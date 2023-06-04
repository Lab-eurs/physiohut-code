package com.example.physiohut.R8;

import java.util.ArrayList;
import java.util.List;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Patient;

public class PatientList {
    private ArrayList<Patient> plist = new ArrayList<Patient>();
    public PatientList(){
        String url = NetworkConstants.getUrlOfFile("r8-populateDropdown.php");
        try{
            R8DataFetcher r8DataFetcher = new R8DataFetcher();
            plist = r8DataFetcher.populateDropDown(url);
            System.out.println(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getAllPatients(){
        return plist;
//        List<String> temp = new ArrayList<String>();
//        for(int i=0; i< plist.size();i++){
//            System.out.println(plist.get(i).getName());
//            temp.add(plist.get(i).getName());
//        }
//        return temp;
    }
}
