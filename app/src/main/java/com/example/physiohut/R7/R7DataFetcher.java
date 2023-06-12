package com.example.physiohut.R7;

import static java.lang.Integer.*;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Appointment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class R7DataFetcher {


    public ArrayList<Appointment> fetchAppointmentsOfDoctor(int doctorID){
        return new ArrayList<>();
    }
    public void markAppointmentAsAccepted(){

    }

    public void markAppointmentAsRejected(){

    }
    public void markAppointmentAsCompleted(){

    }

    public ArrayList<PendingAppointmentsR7> fetchAppointmentsFromDB() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));

        PendingAppointmentsR7 docID = new PendingAppointmentsR7();
        if (docID.getDoctor_id() == 0) {
            docID.setDoctor_id(1);
        }
        String docId = Integer.toString(PendingAppointmentsR7.getDoctor_id());
        System.out.println(docId);
        String url = NetworkConstants.getUrlOfFile("r7-get-pending-appointments-of-doctor.php") + "?doctor_id=" + String.valueOf(docID.getDoctor_id());
        Request request = new Request.Builder().url(url).method("GET", null).build();
        Response response;

        ArrayList<PendingAppointmentsR7> appointments = new ArrayList<>();
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                JSONArray json = new JSONArray(data);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject appointment = json.getJSONObject(i);
                    int appointment_id = appointment.optInt("pending_id");
                    int pat_id = appointment.optInt("patient_id");
                    int doc_id = appointment.optInt("doctor_id");
                    String patientName = appointment.optString("NAME");
                    String date = appointment.optString("created_at");
                    String location = appointment.optString("location");
                    String time = appointment.optString("created_at_time");
                    appointments.add(new PendingAppointmentsR7(appointment_id, doc_id, pat_id, patientName, date, location, time));
                }
            } else {
                // Handle non-successful response
                throw new RuntimeException("Failed to fetch appointments. HTTP status code: " + response.code());
            }
        } catch (IOException | JSONException e) {
            // Handle exceptions
            throw new RuntimeException("Error fetching appointments", e);
        }
        return appointments;
    }

}
