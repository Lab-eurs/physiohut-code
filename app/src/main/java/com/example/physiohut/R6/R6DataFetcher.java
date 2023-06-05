package com.example.physiohut.R6;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Appointment;
import com.example.physiohut.model.Appointments;
import com.example.physiohut.model.Provision;
import com.example.physiohut.model.Session;

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

public class R6DataFetcher {
    public R6DataFetcher() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Appointment> fetchAppointmentsOfDoctor(int doctorID){
//        same as r7
        return new ArrayList<>();
    }

    public ArrayList<Session> fetchWeeklyAppointments(int doctorID) throws Exception {
        ArrayList<Session> sessionsOfPatient = new ArrayList<>();

        String url = NetworkConstants.getUrlOfFile("r7-get-pending-appointments-of-doctor.php") + "?doctor_id=" + doctorID;
        ArrayList<Appointments> cbList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        System.out.println("The URL is --> "+ url);

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
                Appointment a = new Appointment(ap_id,patient_id,doctor_id,scheduledFor,state,completedAt);
                String completed = sessionJSON.getString("sess_completed_at_time");
                Session s = new Session(p,a,sess_state,completed);
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
