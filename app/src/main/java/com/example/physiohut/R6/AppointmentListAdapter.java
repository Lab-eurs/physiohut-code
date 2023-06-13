package com.example.physiohut.R6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;
import com.example.physiohut.model.Session;

import java.util.ArrayList;

public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentViewHolder>{
    public AppointmentListAdapter(ArrayList< Session > data){
        this.localData = data;
    }

    private ArrayList<Session> localData;
    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listview, parent, false);

        return new AppointmentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Session s = localData.get(position);

        holder.getStateTextView().setText(s.getAppointmentID());
        //TODO: put logic here that trims the text to 12 chars max or some amount
        if(s.getSessionState() == Session.SESSION_STATE.COMPLETED){
            holder.getButtonView().setEnabled(false);
            holder.getButtonView().setBackgroundColor(holder.getStateTextView().getResources().getColor(R.color.grey));
        }else if(s.getSessionState() == Session.SESSION_STATE.PENDING){
            holder.getButtonView().setTextColor(holder.getStateTextView().getResources().getColor(R.color.button));
        }


    }

    @Override
    public int getItemCount() {
        return localData.size();
    }

}

