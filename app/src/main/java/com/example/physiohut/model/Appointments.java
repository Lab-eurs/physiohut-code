package com.example.physiohut.model;

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
    String patients_id;

    String doctor_id;


    public String getAppointmentDate(){
        return appointmentDate;
    }

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

    public String getName(){
        return patientName;
    }
}
