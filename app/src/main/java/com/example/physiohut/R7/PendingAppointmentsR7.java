package com.example.physiohut.R7;

public class PendingAppointmentsR7 {

    private int pendingAppointmentId;
    private static int doctor_id;
    private int patient_id;
    private String patientName;
    private String appointmentDate;
    private String appointmentArea;
    private String appointmentTime;

    public boolean isVisible = false;

    public PendingAppointmentsR7() {
        //empty constructor
    }
    public PendingAppointmentsR7(int pendingAppointmentId, int doctor_id, int patient_id, String patientName, String appointmentDate, String appointmentArea, String appointmentTime) {
        this.pendingAppointmentId = pendingAppointmentId;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentArea = appointmentArea;
        this.appointmentTime = appointmentTime;
    }

    public static int getDoctor_id() { return doctor_id; }

    public int getPatient_id() { return patient_id; }

    public String getPatientName() {
        return patientName;
    }
    public int getPendingAppointmentId() { return pendingAppointmentId;}

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentArea() {
        return appointmentArea;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setPendingAppointmentId(int pendingAppointmentId) {
        this.pendingAppointmentId = pendingAppointmentId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentArea(String appointmentArea) {
        this.appointmentArea = appointmentArea;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
}
