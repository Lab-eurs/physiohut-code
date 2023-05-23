package com.example.physiohut;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class R8DataFetcher {

    public R8DataFetcher(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<Patient> populateDropDown(String url) throws Exception {
        ArrayList<Patient> plist = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()){
                String id = keys.next();
                String doc_id = json.get(id).toString();
                String name = json.get(id).toString();
                String address = json.get(id).toString();
                String amka = json.get(id).toString();
                plist.add(new Patient(doc_id,name, address,amka));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return plist;
    }

    ArrayList<Provision> populateProvitionList(String url) throws Exception {
        ArrayList<Provision> prolist = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        try{
            JSONArray json = new JSONArray(data);
            for (int i=0; i<json.length();i++){
                JSONObject provisionJSON = json.getJSONObject(i);
                JSONObject provision = (JSONObject) provisionJSON.get("provision");
                System.out.println(provision);
                int prov_id =  Integer.parseInt((String) provision.get("id"));
                String code = (String) provision.get("CODE");
                String description = (String) provision.get("description");
                Double price = Double.parseDouble((String) provision.get("price"));
                prolist.add(new Provision(prov_id,code,description,price));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return prolist;
    }

    public void logR8(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: "+ response);
    }
}
