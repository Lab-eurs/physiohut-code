package com.example.physiohut.R7;

public class PendingAppointmentsR7 {

    private final int pendingAppointmentId;
    private final int doctor_id;
    private final int patient_id;
    private final String patientName;
    private final String appointmentDate;
   // private String appointmentArea;
   // private String appointmentTime;

    public boolean isVisible = false;


    public PendingAppointmentsR7(int pendingAppointmentId, int doctor_id, int patient_id, String patientName, String appointmentDate) {
        this.pendingAppointmentId = pendingAppointmentId;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }


}
