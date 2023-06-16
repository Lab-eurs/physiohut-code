package com.example.physiohut.R7;

public class PendingAppointmentsR7 {

    private final int pendingAppointmentId;
    private final int doctor_id;
    private final int patient_id;
    private final String patientName;
    private final String appointmentDate;
    private final String appointmentLocation;


    public boolean isVisible = false;


    public PendingAppointmentsR7(int pendingAppointmentId, int doctor_id, int patient_id, String patientName, String appointmentDate,String appointmentLocation) {
        this.pendingAppointmentId = pendingAppointmentId;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentLocation = appointmentLocation;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

}
