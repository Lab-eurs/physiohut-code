package com.example.physiohut;

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

    public ArrayList<Appointments> fetchAppointmentsFromDB() {
        //ping url http://localhost/physiohut_backend/read.php
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //ip Athina 192.168.1.46
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));

        String ip = "192.168.1.46";
        String folder = "physiohut_backend";
        String filename = "appointmentFetcherR7.php";
        String url = "http://" + ip + "/" + folder + "/" + filename;

        Request request = new Request.Builder().url(url).method("GET",null).build();
        Response response;

        ArrayList<Appointments> appointments = new ArrayList<>();
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            String data = response.body().string();

            JSONArray json = new JSONArray(data);
            for(int i =0; i< json.length(); i++){
                JSONObject appointmentJSON = json.getJSONObject(i);
                JSONObject appointment = (JSONObject) appointmentJSON.get("appointment");
                System.out.println(appointment);

                int appointment_id =  Integer.parseInt((String) appointment.get("appointment_id"));
                //int pat_id =  Integer.parseInt((String) appointments.get("patient_id"));
                //int doc_id =  Integer.parseInt((String) appointments.get("doctor_id"));//TODO:Add doctor to the DB
                String patientName = (String) appointment.get("patient_name");
                String date = (String) appointment.get("appointment_date");
                String location = (String) appointment.get("appointment_location");
                String time = (String) appointment.get("appointment_time");
                String status = (String) appointment.get("appointment_status"); //idea: instead of String, use boolean.
                appointments.add(new Appointments(appointment_id ,patientName, date ,location ,time, status));
            }

            return appointments;
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
