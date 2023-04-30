package com.example.physiohut;

import java.util.ArrayList;

public interface DBDataFacade {

    public ArrayList<Patient> getAllPatients();
    public ArrayList<Provision> getProvisionsOfPatientByName(String name);
    public Boolean checkIfCredentialsCorrect(String username,String password);

}
