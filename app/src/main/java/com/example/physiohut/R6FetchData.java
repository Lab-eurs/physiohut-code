package com.example.physiohut;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class R6FetchData {
    public R6FetchData() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<Appointments> populateDropDown(String url) throws Exception {
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
