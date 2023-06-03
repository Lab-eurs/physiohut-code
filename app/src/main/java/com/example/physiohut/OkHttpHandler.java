package com.example.physiohut;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<Patient> getPatientNames(String url) throws Exception {
        ArrayList<Patient> patients = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("DATA RETURNED FROM POST: ");
        try {
            JSONObject json = new JSONObject(data);
            JSONArray patientsArray = json.getJSONArray("patients");
            for (int i = 0; i < patientsArray.length(); i++) {
//                String name = patientsArray.getString(i);
                JSONObject jsonObj = patientsArray.getJSONObject(i);
                String id = jsonObj.getString("id");
                String name = jsonObj.getString("name");
                String address = jsonObj.getString("address");
                String amka = jsonObj.getString("amka");
                String doc_id = jsonObj.getString("doc_id");

                patients.add(new Patient(id,doc_id,name,address,amka));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patients;
    }
}