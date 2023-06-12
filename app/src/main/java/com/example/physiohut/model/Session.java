package com.example.physiohut.model;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Session {
    private Provision provision;
    private Appointment appointment;
    public enum SESSION_STATE {PENDING,REJECTED,COMPLETED}
    private SESSION_STATE sessionState;
    private String completedAt;

    public Session(Provision provision, Appointment appointment, SESSION_STATE sessionState, String completedAt) {
        this.provision = provision;
        this.appointment = appointment;
        this.sessionState = sessionState;
        this.completedAt = completedAt;
    }


    public String toString() {
        return provision.getName() + " " + completedAt;
    }

    public String getAppointmentID(){return  appointment.getId()+" ";}
    public Provision getProvision() {
        return provision;
    }

    public Appointment getAppointment() {
        return appointment;
    }
    public SESSION_STATE getSessionState() {
        return sessionState;
    }

    public void setSessionState(SESSION_STATE sessionState) {
        this.sessionState = sessionState;
    }

    public String getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(String c) {
        completedAt = c;
    }
}
