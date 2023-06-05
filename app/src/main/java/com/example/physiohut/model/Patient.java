package com.example.physiohut.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String id;
    private String doc_id;
    private String name;
    private String address;
    private String AMKA;

    public Patient(String id, String doc_id, String name, String address, String AMKA) {
        this.id = id;
        this.doc_id = doc_id;
        this.name = name;
        this.address = address;
        this.AMKA = AMKA;
    }


    public String toString() {
        return name + "-"  + id;
    }

    public String getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAMKA() {
        return AMKA;
    }

}
