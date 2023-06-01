package com.example.physiohut;

import static java.lang.Integer.*;

import android.os.StrictMode;

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

    public ArrayList<PendingAppointmentsR7> fetchAppointmentsFromDB() {
        //ping url http://localhost/physiohut_backend/read.php
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //ip Athina 192.168.1.46
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));

        String ip = "192.168.2.4";
        String folder = "physiohut_backend";
        String filename = "physiohut-pendingAppointments-script.php";
        PendingAppointmentsR7 docID = new PendingAppointmentsR7();
        if(docID.getDoctor_id() == 0) {
            docID.setDoctor_id(1);
        }
        String docId = Integer.toString(PendingAppointmentsR7.getDoctor_id());
        System.out.println(docId);
        String url = "http://" + ip + "/" + folder + "/" + filename + "/?doctor_id=" + String.valueOf(docID.getDoctor_id());
        Request request = new Request.Builder().url(url).method("GET",null).build();
        Response response;

        ArrayList<PendingAppointmentsR7> appointments = new ArrayList<>();
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            String data = response.body().string();

            JSONArray json = new JSONArray(data);
            for(int i =0; i< json.length(); i++){
                JSONObject appointmentJSON = json.getJSONObject(i);
                JSONObject appointment = (JSONObject) appointmentJSON.get("pending");
                System.out.println(appointment);

                int appointment_id =  parseInt((String) appointment.get("pending_id"));
                int pat_id =  Integer.parseInt((String) appointment.get("patient_id"));
                int doc_id =  Integer.parseInt((String) appointment.get("doctor_id"));
                String patientName = (String) appointment.get("NAME");
                String date = (String) appointment.get("created_at");
                String location = (String) appointment.get("location");
                String time = (String) appointment.get("created_at_time");
                appointments.add(new PendingAppointmentsR7(appointment_id, doc_id, pat_id, patientName, date ,location ,time));
            }

            return appointments;
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
