package com.example.physiohut;

public class Appointments {


    String patientName;
    String appointmentDate;
    String patients_id;

    String doctor_id;


    boolean isVisible = false;

    public String getPatientName() {
        return patientName;
    }

    public Appointments(String patientName, String appointmentDate, String patients_id, String doctor_id) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.patients_id = patients_id;
        this.doctor_id = doctor_id;
    }

    public String getName(){
        return patientName;
    }
}
