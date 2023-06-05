package com.example.physiohut.R10;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Appointment;
import com.example.physiohut.model.Provision;
import com.example.physiohut.model.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import okhttp3.*;
public class R10DataFetcher {


    public ArrayList<Session> fetchCompletedSessionsOfPatient(int patientID){
       ArrayList<Session> sessionsOfPatient = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String url = NetworkConstants.getUrlOfFile("r10-get-patient-sessions.php") + "?patient_id=" +patientID;
        Request request = new Request.Builder().url(url).method("GET",null).build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String data = response.body().string();
            System.out.println("THE RESPONSE IS: " + data);
            JSONArray json = new JSONArray(data);
            for(int i =0; i< json.length(); i++){
                JSONObject sessionJSON = json.getJSONObject(i);

                int prov_id =  Integer.parseInt((String) sessionJSON.get("provision_id"));
                String code = (String) sessionJSON.get("code");
                String description = (String) sessionJSON.get("description");
                Double price = Double.parseDouble((String) sessionJSON.get("price"));
                Provision p = new Provision(prov_id,code,description,price);
                int ap_id = sessionJSON.getInt("appon_id");
                int patient_id = sessionJSON.getInt("patient_id");
                int doctor_id = sessionJSON.getInt("doctor_id");
                String scheduledFor = sessionJSON.getString("date");
                String apState = sessionJSON.getString("ap_state");
                Appointment.APPOINTMENT_STATE state;
                if(apState.equals("completed")){
                    state = Appointment.APPOINTMENT_STATE.COMPLETED;
                }else if (apState.equals("rejected")){
                    state = Appointment.APPOINTMENT_STATE.REJECTED;
                }else if (apState.equals("accepted")){
                    state = Appointment.APPOINTMENT_STATE.ACCEPTED;
                }else{
                    state = Appointment.APPOINTMENT_STATE.PENDING;
                }
                String sessionState = sessionJSON.getString("sess_state");
                Session.SESSION_STATE sess_state;
                if(sessionState.equals("completed")){
                    sess_state = Session.SESSION_STATE.COMPLETED;
                }else if (sessionState.equals("rejected")){
                    sess_state = Session.SESSION_STATE.REJECTED;
                }else{
                    sess_state = Session.SESSION_STATE.PENDING;
                }
                //nullable
                String completedAt = sessionJSON.getString("ap_completed_at_time");
                Appointment a = new Appointment(ap_id,patientID,doctor_id,scheduledFor,state,completedAt);
                Session s = new Session(p,a,sess_state,completedAt);
                sessionsOfPatient.add(s);
            }

            return sessionsOfPatient;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
