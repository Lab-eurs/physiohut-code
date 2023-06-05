package com.example.physiohut.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int patientID;
    private int doctorID;
    private String scheduledFor;
    public enum APPOINTMENT_STATE {ACCEPTED,REJECTED,PENDING,COMPLETED};
    private APPOINTMENT_STATE appointmentState;
    private String completedAt;

    public Appointment(int id, int patientID, int doctorID, String scheduledFor, APPOINTMENT_STATE appointmentState, String completedAt) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.scheduledFor = scheduledFor;
        this.appointmentState = appointmentState;
        this.completedAt = completedAt;
    }

    public int getId() {
        return id;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getScheduledFor() {
        return scheduledFor;
    }

    public APPOINTMENT_STATE getAppointmentState() {
        return appointmentState;
    }

    public String getCompletedAt() {
        return completedAt;
    }
}
