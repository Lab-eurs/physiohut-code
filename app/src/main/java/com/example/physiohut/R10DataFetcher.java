package com.example.physiohut;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.*;
public class R10DataFetcher {

    public ArrayList<Provision> fetchProvisionsFromDbForPatient(long id) {
        //ping url http://localhost/physiohut_backend/get_patient_provisions.php/1
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        int patientID = 1;
        String url = NetworkConstants.getUrlOfFile("get_patient_provisions.php") + "/" + patientID;
        Request request = new Request.Builder().url(url).method("GET",null).build();
        Response response;
        System.out.println("HTTP: GET " + url);
        ArrayList<Provision> provisions = new ArrayList<>();
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            String data = response.body().string();
            System.out.println("THE RESPONSE IS: " + data);
            JSONArray json = new JSONArray(data);
            for(int i =0; i< json.length(); i++){
                JSONObject provisionJSON = json.getJSONObject(i);
                JSONObject provision = (JSONObject) provisionJSON.get("provision");
                System.out.println(provision);
                int prov_id =  Integer.parseInt((String) provision.get("id"));
                String code = (String) provision.get("CODE");
                String description = (String) provision.get("description");
                Double price = Double.parseDouble((String) provision.get("price"));
                provisions.add(new Provision(prov_id,code,description,price));
            }

            return provisions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
        //get results in 0=>, 1=> format will see how it goes



    public ArrayList<Provision> fetchProvisionsFromDbForPatient(String name){
        return null;
    }
}
