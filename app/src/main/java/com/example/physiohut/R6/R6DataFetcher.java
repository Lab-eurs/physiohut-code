package com.example.physiohut.R6;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Appointment;
import com.example.physiohut.model.Appointments;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class R6DataFetcher {
    public R6DataFetcher() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Appointment> fetchAppointmentsOfDoctor(int doctorID){
//        same as r7
        return new ArrayList<>();
    }

    public ArrayList<Appointments> fetchWeeklyAppointments(int patientID) throws Exception {
        String url = NetworkConstants.getUrlOfFile("r6-r10-get_patient_provisions.php") + "?patient_id=" + patientID;
        ArrayList<Appointments> cbList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        System.out.println("The URL is --> "+ url);
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My Response: " + data);
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()) {
                String name = keys.next();
                String patients_id = json.getJSONObject(name).getString("ids").toString();
                String doctor_id = json.getJSONObject(name).getString("doctors").toString();
                String created_at = json.getJSONObject(name).getString("created_at").toString();
                cbList.add(new Appointments(name, created_at, patients_id, doctor_id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cbList;
    }

}
