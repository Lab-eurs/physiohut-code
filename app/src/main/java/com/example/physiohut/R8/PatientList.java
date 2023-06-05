package com.example.physiohut.R8;

import java.util.ArrayList;
import java.util.List;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Patient;

public class PatientList {
    private ArrayList<Patient> plist = new ArrayList<Patient>();
    public PatientList(int doctorID){
//        String url = NetworkConstants.getUrlOfFile("r8-populateDropdown.php");
        try{
            R8DataFetcher r8DataFetcher = new R8DataFetcher();
            plist = r8DataFetcher.getPatientsOfDoctor(doctorID);
            System.out.println("Result of fetching patients: " + plist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getAllPatients(){
        return plist;}
}
