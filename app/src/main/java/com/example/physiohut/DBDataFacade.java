package com.example.physiohut;

import java.util.ArrayList;

public interface IDataFetcher {

    public ArrayList<Patient> getAllPatients();
    public ArrayList<Provision> getProvisionsOfPatientByName(String name);
    public Boolean checkIfCredentialsCorrect(String )

}
