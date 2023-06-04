package com.example.physiohut.R8;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Patient;
import com.example.physiohut.model.Provision;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class R8DataFetcher {

    public R8DataFetcher(){
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Patient> fetchPatients(int doctorID){
//        String url =
        return new ArrayList<>();
    }

    public ArrayList<Provision> fetchProvisions() throws  Exception{
        ArrayList<Provision> provisions = new ArrayList<>();
        String url = NetworkConstants.getUrlOfFile("r8-fetch-provisions.php");
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
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
    }

    public void markSessionAsCompleted(){

    }

    public void createAppointment(int doctorID,String patientName,ArrayList<String> provisionCodes,String dateScheduledFor){
        System.out.println("CREATING APPOINTMENT WITH: " + doctorID + patientName + provisionCodes + dateScheduledFor);
    }



    public ArrayList<Patient> populateDropDown(String url) throws Exception{
        ArrayList<Patient> plist = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",body).build();
        System.out.println("The URL is --> "+ url);
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My Response: " + data);
        try{
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()){
                String name = keys.next();
                String id = json.getJSONObject(name).getString("ids").toString();
                String doc_id = json.getJSONObject(name).getString("doctors").toString();
                String address = json.getJSONObject(name).getString("addresses").toString();
                String amka = json.getJSONObject(name).getString("amkas").toString();
                plist.add(new Patient(id, doc_id,name,address,amka));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return plist;
    }


    public ArrayList<Provision> populateProvitionList(String ip){
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //ip grafeio 172.23.240.1
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        String url = NetworkConstants.getUrlOfFile("PopulateList.php");
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
            Iterator<String> keys = json.keys();
            while (keys.hasNext()){
                String code = keys.next();
                String prov_id = json.getJSONObject(code).getString("ids").toString();
                String description = json.getJSONObject(code).getString("descriptions").toString();
                String price = json.getJSONObject(code).getString("prices").toString();
                provisions.add(new Provision(prov_id,code,description,price));
            }

            return provisions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void logR8(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: "+ response);
    }

    public void katagrafiR8(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: "+ response);
    }
}
