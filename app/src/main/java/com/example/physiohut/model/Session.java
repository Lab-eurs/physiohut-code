package com.example.physiohut.model;

import java.time.LocalDateTime;

public class Session {
    private Provision provision;
    private Appointment appointment;
    public enum SESSION_STATE {PENDING,REJECTED,COMPLETED}
    private SESSION_STATE sessionState;
    private LocalDateTime completedAt;

    public Session(Provision provision, Appointment appointment, SESSION_STATE sessionState, LocalDateTime completedAt) {
        this.provision = provision;
        this.appointment = appointment;
        this.sessionState = sessionState;
        this.completedAt = completedAt;
    }

    public Provision getProvision() {
        return provision;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public SESSION_STATE getSessionState() {
        return sessionState;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
