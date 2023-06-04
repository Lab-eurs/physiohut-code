package com.example.physiohut.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int patientID;
    private int doctorID;
    private LocalDateTime scheduledFor;
    public enum APPOINTMENT_STATE {ACCEPTED,REJECTED,PENDING,COMPLETED};
    private APPOINTMENT_STATE appointmentState;
    private LocalDateTime completedAt;

    public Appointment(int id, int patientID, int doctorID, LocalDateTime scheduledFor, APPOINTMENT_STATE appointmentState, LocalDateTime completedAt) {
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

    public LocalDateTime getScheduledFor() {
        return scheduledFor;
    }

    public APPOINTMENT_STATE getAppointmentState() {
        return appointmentState;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
