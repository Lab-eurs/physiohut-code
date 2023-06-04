package com.example.physiohut.R1;

import android.os.StrictMode;

import com.example.physiohut.AuthenticateUser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class R1DataFetcher {
    public R1DataFetcher(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void createDoctor(String url) throws Exception{
    OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String idOfDoctor = response.body().string();
        //setting this for use in other places
        AuthenticateUser.setDoctorID(Integer.parseInt(idOfDoctor));

    }
}
