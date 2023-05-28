package com.example.physiohut;

public class Appointments {

    private int appointmentId;
    private String patientName;
    private String appointmentDate;
    private String appointmentArea;
    private String appointmentTime;
    private String appointmentStatus;

    boolean isVisible = false;

    public Appointments(int appointment_id, String patientName, String appointmentDate, String appointmentArea,
                        String appointmentTime, String status) {
        this.appointmentId = appointment_id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentArea = appointmentArea;
        this.appointmentTime = appointmentTime;
        this.appointmentStatus = status;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentArea() {
        return appointmentArea;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }
}
