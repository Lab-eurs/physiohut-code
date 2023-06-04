package com.example.physiohut.R4;

import android.os.*;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Provision;

import org.json.*;

import java.io.IOException;
import java.util.*;
import okhttp3.*;

public class R4DataFetcher {
    public R4DataFetcher() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Provision> PopulateRecycleView(int doctorID, int patientID){
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        System.out.println("POPULATING RECYCLERVIEW");
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        String url = NetworkConstants.getUrlOfFile("r4-populatePatientHistory.php");
        Request request = new Request.Builder().url(url).method("GET",null).build();
        Response response;
        System.out.println("THE URL IS -->" + url);
        ArrayList<Provision> provisions = new ArrayList<>();
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            String data = response.body().string();
            System.out.println("THE RESPONSE IS: " + data);

            JSONObject json = new JSONObject(data);
            if(!json.keys().hasNext())return new ArrayList<>();
            Iterator<String> keys = json.keys();
            while (keys.hasNext()){
                String code = keys.next();
                String prov_id = json.getJSONObject(code).getString("ids").toString();
                String date = json.getJSONObject(code).getString("dates").toString();
                String description = json.getJSONObject(code).getString("descriptions").toString();
                String price = json.getJSONObject(code).getString("prices").toString();
                provisions.add(new Provision(prov_id,date,code,description,price));
            }
            response.close();
            return provisions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

