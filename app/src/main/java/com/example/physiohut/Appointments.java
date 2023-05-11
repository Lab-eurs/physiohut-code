package com.example.physiohut;

public class Appointments {


    String patientName;
    String appointmentDate;
    String appointmentArea;
    String appointmentTime;

    boolean isVisible = false;

    public String getPatientName() {
        return patientName;
    }

    public Appointments(String patientName, String appointmentDate, String appointmentArea, String appointmentTime) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentArea = appointmentArea;
        this.appointmentTime = appointmentTime;
    }
}
