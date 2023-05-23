package com.example.physiohut;

import android.os.*;
import org.json.*;

import java.io.IOException;
import java.util.*;
import okhttp3.*;

public class R4DB {
    public R4DB() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<Provision> PopulateRecycleView(String url) throws Exception{
        ArrayList<Provision> provisions = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        System.out.println("THE URL IS -->" + url);
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
}

