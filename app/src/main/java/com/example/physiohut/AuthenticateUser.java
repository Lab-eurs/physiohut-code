package com.example.physiohut;

public class AuthenticateUser {

    public static int doctorID = 2;
    public static void setDoctorID(int id){
        doctorID = id;
    }
    public static int patientID = 1;
    public static void setPatientID(int id){
        patientID = id;
    }
    private int doctorId;
    private int patientId;
    public int getAuthenticatedUser(String username,String password){
        return -1;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
