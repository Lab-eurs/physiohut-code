package com.example.physiohut.R8;

import java.util.ArrayList;
import java.util.List;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.Patient;
import com.example.physiohut.R8.*;

public class PatientList {
    private ArrayList<Patient> plist = new ArrayList<Patient>();
    public PatientList(){
        String url = NetworkConstants.getUrlOfFile("populateDropdown.php");
//        String url = "http://"+ip+"/physiohutDBServices/populateDropDown.php";
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
