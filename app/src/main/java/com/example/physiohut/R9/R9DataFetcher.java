package com.example.physiohut.R9;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class R9DataFetcher {

    public R9DataFetcher() {

    }

    public void requestAppointment(int patientID,String date){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String url = NetworkConstants.getUrlOfFile("r9-request-appointment.php") + "?patient_id=" + patientID + "&date="+date;
        Request request = new Request.Builder().url(url).method("GET",null).build();
        Response response=null;
        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            String data = response.body().string();
            System.out.println("THE RESPONSE IS: " + data);

        }catch(Exception e){

        }finally{

            if(response != null)response.close();
        }
    }

    public void logHistory(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);
    }
}
