package com.example.physiohut;

public class Appointments {


    String patientName;
    String appointmentDate;
    String appointmentArea;
    String appointmentTime;
    String comments;
    String provisions;
    String id;
    String patient;
    String doctor;
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

    public Appointments(String id, String patient, String doctor, String comments, String provisions, String appointmentDate){
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.comments = comments;
        this.provisions = provisions;
        this.appointmentDate = appointmentDate;
    }

    public String getProvisions(){
        return provisions;
    }
}
