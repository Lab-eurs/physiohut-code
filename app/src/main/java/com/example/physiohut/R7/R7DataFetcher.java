package com.example.physiohut.R7;

import static java.lang.Integer.*;

import android.os.StrictMode;

import com.example.physiohut.NetworkConstants;
import com.example.physiohut.model.Appointment;
import com.example.physiohut.model.Appointments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class R7DataFetcher {


    public ArrayList<Appointment> fetchAppointmentsOfDoctor(int doctorID){
        return new ArrayList<>();
    }
    public void markAppointmentAsAccepted(){

    }

    public void markAppointmentAsRejected(){

    }
    public void markAppointmentAsCompleted(){

    }

    public ArrayList<PendingAppointmentsR7> fetchAppointmentsFromDB(int doctorID) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<PendingAppointmentsR7> appointments = new ArrayList<>();

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
            System.out.println("The response is: "+data);
            JSONArray json = new JSONArray(data);
            for(int i=0;i<json.length();i++){
                JSONObject pendingapointmentJSON = json.getJSONObject(i);

                int ap_id = pendingapointmentJSON.getInt("appon_id");
                int doctor_id = pendingapointmentJSON.getInt("doctor_id");
                int patient_id = pendingapointmentJSON.getInt("patient_id");
                String patientName = pendingapointmentJSON.getString("patient_name");
                String apointDate = pendingapointmentJSON.getString("scheduled_for");
                String appointmentLocation = pendingapointmentJSON.getString("doctor_address");

                appointments.add(new PendingAppointmentsR7(ap_id,doctor_id,patient_id,patientName,apointDate,appointmentLocation));
            }
            return appointments;
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (JSONException e){
            throw new RuntimeException(e);
        }

    }

}
