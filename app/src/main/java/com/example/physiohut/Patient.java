package com.example.physiohut;

public class Patient {

    private String name;
    private String address;
    private String AMKA;

    public Patient(String name, String address, String AMKA) {
        this.name = name;
        this.address = address;
        this.AMKA = AMKA;
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
