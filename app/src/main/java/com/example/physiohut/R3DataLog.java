package com.example.physiohut;

import android.os.StrictMode;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class R3DataLog {
    public R3DataLog(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void physioLog(String url) throws Exception{
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);

    }
}
